package net.cakesolutions.interview.examples

import net.cakesolutions.interview.Monoid

object MonoidExamples {

  /**
    * The simplest example of monoidal structure
    */
  implicit val additiveMonoid: Monoid[Int] = new Monoid[Int] {
    override def mappend(a1: Int, a2: Int): Int = a1 + a2
    override def mzero: Int = 0
  }

  /**
    * Note also, the standard Int forms a monoid under (Int, *, 1).
    * This example is to avoid a conflict with the previously defined implicit
    */
  implicit val pairMultiplicativeMonoid: Monoid[(Int, Int)] =
    new Monoid[(Int, Int)] {
      override def mappend(a1: (Int, Int), a2: (Int, Int)): (Int, Int) =
        (a1._1 * a2._1, a1._2 * a2._2)
      override def mzero: (Int, Int) = (1, 1)
    }

  /**
    * In fact, this is independent of the choice of `A`. Ostensibly, List is a *free* monoid
    * in a principled language/theory.
    */
  implicit def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
    override def mappend(a1: List[A], a2: List[A]): List[A] = a1 ::: a2
    override def mzero: List[A] = Nil
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def mappend(a1: String, a2: String): String = a1 + a2
    override def mzero: String = ""
  }

  // TODO: Very extra credit. How would you go about creating a
  // TODO: monoid instance for binary trees?
  implicit def treeMonoid[A]: Monoid[Tree[A]] = ???
}
