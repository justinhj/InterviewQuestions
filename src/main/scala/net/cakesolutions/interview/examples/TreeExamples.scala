package net.cakesolutions.interview.examples

import net.cakesolutions.interview._

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

  implicit val treeFoldable: Foldable[Tree] = new Foldable[Tree] {
    override def foldr[A, B](fa: Tree[A])(z: => B)(f: A => B => B): B =
      fa match {
        case Branch(a, l, r) =>
          foldr(r)(f(a)(foldr(l)(z)(f)))(f) // Those parentheses tho
        case Tip() => z
      }
  }

  implicit val treeTraversable: Traversable[Tree] = new Traversable[Tree] {
    override def traverse[A, B, F[_]](ta: Tree[A])(k: A => F[B])(
        implicit A: Applicative[F]): F[Tree[B]] =
      ta match {
        case Tip() => A.pure(Tip())
        case Branch(a, l, r) =>
          A.liftA3(k(a))(traverse(l)(k))(traverse(r)(k))(aa =>
            ll => rr => Branch(aa, ll, rr))
      }

    override def foldr[A, B](fa: Tree[A])(z: => B)(f: A => B => B): B =
      treeFoldable.foldr(fa)(z)(f)

    override def fmap[A, B](fa: Tree[A])(f: A => B): Tree[B] =
      treeFunctor.fmap(fa)(f)
  }

}
