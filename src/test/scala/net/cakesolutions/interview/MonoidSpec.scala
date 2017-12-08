package net.cakesolutions.interview

import net.cakesolutions.interview.examples.MonoidExamples._
import org.scalatest.{Matchers, WordSpec}

class MonoidSpec extends WordSpec with Matchers {

  def associative[M](m1: M, m2: M, m3: M)(implicit m: Monoid[M]): Boolean =
    m.mappend(m.mappend(m1)(m2))(m3) == m.mappend(m1)(m.mappend(m2)(m3))

  def identity[M](m1: M)(implicit m: Monoid[M]): Boolean =
    m.mappend(m1)(m.mzero) == m1 && (m.mappend(m.mzero)(m1)) == m1

  "Example Monoids" should {
    "satisfy the associativity laws" in {
      associative(1, 2, 3) shouldBe true
      associative((1, 1), (2, 2), (3, 3)) shouldBe true
      associative("lol", "foo", "bar") shouldBe true
      associative(List(1), List(2), List(3)) shouldBe true
    }

    "satisfy the identity laws" in {
      identity(1) shouldBe true
      identity((1, 1)) shouldBe true
      identity("lol") shouldBe true
      identity(List(1)) shouldBe true
    }
  }

}
