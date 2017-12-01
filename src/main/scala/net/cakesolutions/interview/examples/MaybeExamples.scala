package net.cakesolutions.interview.examples

import net.cakesolutions.interview._

sealed trait Maybe[A]
final case class Just[A](a: A) extends Maybe[A]
final case class Nothing[A]() extends Maybe[A]

object Maybe {

  implicit val maybeFunctor: Functor[Maybe] = new Functor[Maybe] {
    override def fmap[A, B](f: A => B)(fa: Maybe[A]): Maybe[B] =
      fa match {
        case Just(a) => Just(f(a))
        case _       => Nothing()
      }
  }

  implicit val maybeApply: Apply[Maybe] = new Apply[Maybe] {
    override def pure[A](a: A): Maybe[A] = Just(a)
  }

  implicit val maybeApplicative: Applicative[Maybe] = new Applicative[Maybe] {
    override def ap[A, B](ff: Maybe[A => B])(fa: Maybe[A]): Maybe[B] =
      ff match {
        case Just(f) =>
          maybeFunctor.fmap(f)(fa)
        case _ => Nothing()
      }
    override def pure[A](a: A): Maybe[A] = maybeApply.pure(a)
    override def fmap[A, B](f: A => B)(fa: Maybe[A]): Maybe[B] =
      maybeFunctor.fmap(f)(fa)
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
    override def pure[A](a: A): Maybe[A] = maybeApply.pure(a)
    override def fmap[A, B](f: A => B)(fa: Maybe[A]): Maybe[B] =
      maybeFunctor.fmap(f)(fa)
  }

  implicit val maybeFoldable: Foldable[Maybe] = new Foldable[Maybe] {
    override def foldr[A, B](f: (A, B) => B)(z: B)(fa: Maybe[A]): B =
      fa match {
        case Just(a)   => f(a, z)
        case Nothing() => z
      }
  }

  implicit val maybeTraversable: Traversable[Maybe] = new Traversable[Maybe] {

    override def traverse[A, B, F[_]](k: A => F[B])(ta: Maybe[A])(
        implicit A: Applicative[F]): F[Maybe[B]] =
      sequenceA[B, F](fmap(k)(ta))

    override def sequenceA[A, F[_]](tfa: Maybe[F[A]])(
        implicit A: Applicative[F]): F[Maybe[A]] =
      traverse[F[A], A, F](identity)(tfa)

    override def foldr[A, B](f: (A, B) => B)(z: B)(fa: Maybe[A]): B =
      maybeFoldable.foldr(f)(z)(fa)
    override def fmap[A, B](f: A => B)(fa: Maybe[A]): Maybe[B] =
      maybeFunctor.fmap(f)(fa)
  }
}
