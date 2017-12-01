package net.cakesolutions.interview.examples

import net.cakesolutions.interview.Monoid

object MonoidExamples {

  implicit val additiveMonoid: Monoid[Int] = new Monoid[Int] {
    override def mappend(a1: Int, a2: Int): Int = a1 + a2
    override def mzero: Int = 0
  }

  implicit val multiplicativeMonoid: Monoid[Int] = new Monoid[Int] {
    override def mappend(a1: Int, a2: Int): Int = a1 * a2
    override def mzero: Int = 1
  }

  implicit def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
    override def mappend(a1: List[A], a2: List[A]): List[A] = a1 ::: a2
    override def mzero: List[A] = Nil
  }

  // TODO: Very extra credit. How would you go about creating a
  // TODO: monoid instance for binary trees?
  implicit def treeMonoid[A]: Monoid[Tree[A]] = ???
}
