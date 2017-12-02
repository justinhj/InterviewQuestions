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
    override def fmap[A, B](fa: Tree[A])(f: A => B): Tree[B] =
      fa match {
        case Branch(a, l, r) => Branch(f(a), fmap(l)(f), fmap(r)(f))
        case Tip()           => Tip()
      }
  }

  implicit val treeApply: Apply[Tree] = new Apply[Tree] {
    override def pure[A](a: A): Tree[A] = Branch(a, Tip(), Tip())
  }

  implicit val treeFoldable: Foldable[Tree] = new Foldable[Tree] {
    override def foldr[A, B](fa: Tree[A])(z: B)(f: (A, B) => B): B =
      fa match {
        case Branch(a, l, r) =>
          foldr(r)(f(a, foldr(l)(z)(f)))(f)
        case Tip() => z
      }
  }

  implicit val treeTraversable: Traversable[Tree] = new Traversable[Tree] {

    override def traverse[A, B, F[_]](ta: Tree[A])(k: A => F[B])(
        implicit A: Applicative[F]): F[Tree[B]] =
      sequenceA[B, F](fmap(ta)(k))

    override def sequenceA[A, F[_]](tfa: Tree[F[A]])(
        implicit A: Applicative[F]): F[Tree[A]] =
      traverse(tfa)(identity)

    override def foldr[A, B](fa: Tree[A])(z: B)(f: (A, B) => B): B =
      foldr(fa)(z)(f)

    override def fmap[A, B](fa: Tree[A])(f: A => B): Tree[B] =
      fmap(fa)(f)
  }

  // TODO:
  // Why wouldn't this work/be useful for us? this is very extra credit.
  // Futher, why doesn't this version of a binary tree have a monad or applicative instance?
  // It has to do with infinite trees and non-empty tips
  // implicit val treeApplicative: Applicative[Tree] = ???

}
