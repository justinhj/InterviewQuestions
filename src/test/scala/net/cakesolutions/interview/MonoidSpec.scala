package net.cakesolutions.interview

import org.scalatest.{Matchers, WordSpec}

class MonoidSpec extends WordSpec with Matchers {

  def associative[M](m1: M, m2: M, m3: M)(implicit m: Monoid[M]): Boolean =
    m.mappend(m.mappend(m1)(m2))(m3) == m.mappend(m1)(m.mappend(m2)(m3))

  def identity[M](m1: M)(implicit m: Monoid[M]): Boolean =
    m.mappend(m1)(m.mzero) == (m.mappend(m.mzero)(m1)) == m

}
