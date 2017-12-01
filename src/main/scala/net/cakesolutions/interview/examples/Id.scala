package net.cakesolutions.interview.examples

import net.cakesolutions.interview.{Applicative, Apply, Foldable, Functor, Id, Monad, Traversable}

object Id {

  implicit val idFunctor: Functor[Id] = new Functor[Id] {
    override def fmap[A, B](f: A => B)(fa: Id[A]): Id[B] = f(fa)
  }

  implicit val idApply: Apply[Id] = new Apply[Id] {
    override def pure[A](a: A): Id[A] = a
  }

  implicit val idApplicative: Applicative[Id] = new Applicative[Id] {
    override def ap[A, B](ff: Id[A => B])(fa: Id[A]): Id[B] = ff(fa)
    override def pure[A](a: A): Id[A] = idApply.pure(a)
    override def fmap[A, B](f: A => B)(fa: Id[A]): Id[B] = idFunctor.fmap(f)(fa)
  }

  implicit val idMonad: Monad[Id] = new Monad[Id] {
    override def bind[A, B](ma: Id[A])(k: A => Id[B]): Id[B] = k(ma)
    override def ap[A, B](ff: Id[A => B])(fa: Id[A]): Id[B] = idApplicative.ap(ff)(fa)
    override def pure[A](a: A): Id[A] = idApply.pure(a)
    override def fmap[A, B](f: A => B)(fa: Id[A]): Id[B] = idFunctor.fmap(f)(fa)
  }
  implicit val idFoldable: Foldable[Id] = new Foldable[Id] {
    override def foldr[A, B](f: (A, B) => B)(z: B)(fa: Id[A]): B = f(fa, z)
  }

  implicit val idTraversable: Traversable[Id] = new Traversable[Id] {
    override def traverse[A, B, F[_]](k: A => F[B])(ta: Id[A])(implicit A: Applicative[F]): F[Id[B]] =
      k(ta)
    override def sequenceA[A, F[_]](tfa: Id[F[A]])(implicit A: Applicative[F]): F[Id[A]] =
      traverse[F[A], A, F](identity)(tfa)

    override def foldr[A, B](f: (A, B) => B)(z: B)(fa: Id[A]): B = idFoldable.foldr(f)(z)(fa)
    override def fmap[A, B](f: A => B)(fa: Id[A]): Id[B] = idFunctor.fmap(f)(fa)
  }

}
