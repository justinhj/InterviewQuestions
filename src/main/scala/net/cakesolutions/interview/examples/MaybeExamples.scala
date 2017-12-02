package net.cakesolutions.interview.examples

import net.cakesolutions.interview._

sealed trait Maybe[A]
final case class Just[A](a: A) extends Maybe[A]
final case class Nothing[A]() extends Maybe[A]

object MaybeExamples {

  implicit val maybeFunctor: Functor[Maybe] = new Functor[Maybe] {
    override def fmap[A, B](fa: Maybe[A])(f: A => B): Maybe[B] =
      fa match {
        case Just(a) => Just(f(a))
        case _       => Nothing()
      }
  }

  implicit val maybeFoldable: Foldable[Maybe] = new Foldable[Maybe] {
    override def foldr[A, B](fa: Maybe[A])(z: => B)(f: A => B => B): B =
      fa match {
        case Just(a)   => f(a)(z)
        case Nothing() => z
      }
  }

  implicit val maybeApplicative: Applicative[Maybe] = new Applicative[Maybe] {
    override def ap[A, B](ff: Maybe[A => B])(fa: Maybe[A]): Maybe[B] =
      ff match {
        case Just(f) => fmap(fa)(f)
        case _       => Nothing()
      }
    override def pure[A](a: A): Maybe[A] = Just(a)
    override def fmap[A, B](fa: Maybe[A])(f: A => B): Maybe[B] =
      maybeFunctor.fmap(fa)(f)
  }

  implicit val maybeTraversable: Traversable[Maybe] = new Traversable[Maybe] {
    override def traverse[A, B, F[_]: Applicative](ta: Maybe[A])(
        k: A => F[B]): F[Maybe[B]] =
      sequenceA[B, F](fmap(ta)(k))
    override def sequenceA[A, F[_]: Applicative](
        tfa: Maybe[F[A]]): F[Maybe[A]] =
      traverse(tfa)(identity)
    override def foldr[A, B](fa: Maybe[A])(z: => B)(f: A => B => B): B =
      maybeFoldable.foldr(fa)(z)(f)
    override def fmap[A, B](fa: Maybe[A])(f: A => B): Maybe[B] =
      maybeFunctor.fmap(fa)(f)
  }

  implicit val maybeMonad: Monad[Maybe] = new Monad[Maybe] {
    override def bind[A, B](ma: Maybe[A])(k: A => Maybe[B]): Maybe[B] =
      ma match {
        case Just(a) => k(a)
        case _       => Nothing()
      }

    /** Note the very simple implementation */
    override def join[A, B](mma: Maybe[Maybe[A]]): Maybe[A] =
      bind(mma)(identity)
    override def ap[A, B](ff: Maybe[A => B])(fa: Maybe[A]): Maybe[B] =
      maybeApplicative.ap(ff)(fa)
    override def pure[A](a: A): Maybe[A] = maybeApplicative.pure(a)
    override def fmap[A, B](fa: Maybe[A])(f: A => B): Maybe[B] =
      maybeFunctor.fmap(fa)(f)
  }

}
