package net.cakesolutions.interview

/**
  * Monoids are abstract algebraic structures that admit a
  * law of associativity (i.e. the laws (a . b) . c = a . (b . c)
  * for any closed binary operation (.): A x A => A sending pairs
  * of elements of the same type to elements of the same type,
  * and also admit an identity element, or fixed point for the
  * associated binary operation (.).
  *
  * Conventionally, we alias (.) with `mappend`, or the symbol |+|
  *
  */
trait Monoid[A] {

  def mappend(a1: A, a2: A): A

  def mzero: A

}
