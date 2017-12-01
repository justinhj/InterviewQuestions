package net.cakesolutions.interview

/**
  * Monoids are abstract algebraic structures that admit a
  * law of associativity (i.e. the laws (a . b) . c = a . (b . c)
  * for any closed binary operation `(.): A x A => A` sending pairs
  * of elements of the same type to elements of the same type,
  * and also admit an identity element, or fixed point for the
  * associated binary operation (.).
  *
  * Conventionally, we alias (.) with `mappend`, or the symbol |+|
  *
  * Let `M` be a set, and `|+|:MxM -> M` be a closed binary operation, then,
  * in order for M to be called a Monoid, it must satisfy the following laws:
  *
  *   `Two-sided identity`:
  *     {{{∃ e ϵ M, ∀m ϵ M . m |+| e = e |+| m = m}}}
  *
  *   `Associativity`
  *     {{{∀m, n, o ϵ M . (m |+| n) |+| o = m |+| (n |+| o)}}}
  *
  *   `Closure`
  *     {{{∀m, m' ϵ M, m |+| m' ϵ M (usually assumed)}}}
  *
  */
trait Monoid[A] {

  /**
    * A closed, associative binary operation.
    */
  def mappend(a1: A, a2: A): A

  /**
    * The fixed point identity of the binary operation
    */
  def mzero: A

}
