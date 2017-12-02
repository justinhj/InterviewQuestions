package net.cakesolutions.interview.examples

import net.cakesolutions.interview.{
  Applicative,
  Apply,
  Foldable,
  Functor,
  Traversable
}

sealed trait Tree[A]
final case class Branch[A](a: A, left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Tip[A]() extends Tree[A]

object TreeExamples {

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def fmap[A, B](f: A => B)(fa: Tree[A]): Tree[B] =
      fa match {
        case Branch(a, l, r) => Branch(f(a), fmap(f)(l), fmap(f)(r))
        case Tip()           => Tip()
      }
  }

  implicit val treeApply: Apply[Tree] = new Apply[Tree] {
    override def pure[A](a: A): Tree[A] = Branch(a, Tip(), Tip())
  }

  implicit val treeFoldable: Foldable[Tree] = new Foldable[Tree] {
    override def foldr[A, B](f: (A, B) => B)(z: B)(fa: Tree[A]): B =
      fa match {
        case Branch(a, l, r) =>
          foldr(f)(f(a, foldr(f)(z)(l)))(r)
        case Tip() => z
      }
  }

  implicit val treeTraversable: Traversable[Tree] = new Traversable[Tree] {

    override def traverse[A, B, F[_]](k: A => F[B])(ta: Tree[A])(
        implicit A: Applicative[F]): F[Tree[B]] =
      sequenceA[B, F](fmap(k)(ta))

    override def sequenceA[A, F[_]](tfa: Tree[F[A]])(
        implicit A: Applicative[F]): F[Tree[A]] =
      traverse[F[A], A, F](identity)(tfa)

    override def foldr[A, B](f: (A, B) => B)(z: B)(fa: Tree[A]): B =
      treeFoldable.foldr(f)(z)(fa)

    override def fmap[A, B](f: A => B)(fa: Tree[A]): Tree[B] =
      treeFunctor.fmap(f)(fa)
  }

  // TODO:
  // Why wouldn't this work/be useful for us? this is very extra credit.
  // Futher, why doesn't this version of a binary tree have a monad or applicative instance?
  // It has to do with infinite trees and non-empty tips
  // implicit val treeApplicative: Applicative[Tree] = ???

}
