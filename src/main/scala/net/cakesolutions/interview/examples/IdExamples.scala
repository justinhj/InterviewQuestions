package net.cakesolutions.interview.examples

import net.cakesolutions.interview.{
  Applicative,
  Apply,
  Foldable,
  Functor,
  Id,
  Monad,
  Traversable
}

object IdExamples {

  implicit val idFunctor: Functor[Id] = new Functor[Id] {
    override def fmap[A, B](fa: Id[A])(f: A => B): Id[B] = f(fa)
  }

  implicit val idApply: Apply[Id] = new Apply[Id] {
    override def pure[A](a: A): Id[A] = a
  }

  implicit val idApplicative: Applicative[Id] = new Applicative[Id] {
    override def ap[A, B](ff: Id[A => B])(fa: Id[A]): Id[B] = ff(fa)
    override def pure[A](a: A): Id[A] = pure(a)
    override def fmap[A, B](fa: Id[A])(f: A => B): Id[B] = fmap(fa)(f)
  }

  implicit val idMonad: Monad[Id] = new Monad[Id] {
    override def bind[A, B](ma: Id[A])(k: A => Id[B]): Id[B] = k(ma)
    override def ap[A, B](ff: Id[A => B])(fa: Id[A]): Id[B] = ap(ff)(fa)
    override def pure[A](a: A): Id[A] = pure(a)
    override def fmap[A, B](fa: Id[A])(f: A => B): Id[B] = fmap(fa)(f)
  }
  implicit val idFoldable: Foldable[Id] = new Foldable[Id] {
    override def foldr[A, B](fa: Id[A])(z: B)(f: (A, B) => B): B = f(fa, z)
  }

  implicit val idTraversable: Traversable[Id] = new Traversable[Id] {
    override def traverse[A, B, F[_]: Applicative](ta: Id[A])(
        k: A => F[B]): F[Id[B]] = k(ta)
    override def sequenceA[A, F[_]: Applicative](tfa: Id[F[A]]): F[Id[A]] =
      traverse(tfa)(identity)
    override def foldr[A, B](fa: Id[A])(z: B)(f: (A, B) => B): B =
      foldr(fa)(z)(f)
    override def fmap[A, B](fa: Id[A])(f: A => B): Id[B] = fmap(fa)(f)
  }

}
