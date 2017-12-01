package net.cakesolutions.interview.examples

import net.cakesolutions.interview.{Applicative, Apply, Foldable, Functor}


sealed trait Tree[A]
final case class Branch[A](a: A, left: Tree[A], right: Tree[A]) extends Tree[A]
//I make it invariant, because covariance in Scala is broken.
final case class Tip[A]() extends Tree[A]

object Tree {

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def fmap[A, B](f: A => B)(fa: Tree[A]): Tree[B] =
      fa match {
        case Branch(a, l, r) => Branch(f(a), fmap(f)(l), fmap(f)(r))
        case Tip() => Tip()
      }
  }

  implicit val treeApply: Apply[Tree] = new Apply[Tree] {
    override def pure[A](a: A): Tree[A] = Branch(a, Tip(), Tip())
  }

  // Why wouldn't this work/be useful for us? this is very extra credit.
  // Futher, why don't this version of a binary tree have a monad instance?
  // It has to do with infinite trees and non-empty tips
  implicit val treeApplicative: Applicative[Tree] = ???

}
