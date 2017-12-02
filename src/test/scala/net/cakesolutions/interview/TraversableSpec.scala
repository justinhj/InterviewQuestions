package net.cakesolutions.interview

import org.scalatest.{Matchers, WordSpec}

class TraversableSpec extends WordSpec with Matchers {

  import instances._, examples._

  /**
    * This should help you get started testing. The syntax T[_] : [[Traversable]]
    * is what we call a "context bound". It's syntactic sugar for [T[_]](implicit ev: [[Traversable]][T]),
    * i.e. for each type parameter with a context bound, you ask for an associated implicit. You
    * are then able to access the implicits explicitly using the `implicitly` keyword.
    *
    * Go ahead and try it out on some of the [[examples]]!
    *
    * @param ta the [[Traversable]]
    * @param k some function inserting a traversable value into an Applicative context
    * @tparam T a [[Traversable]]
    * @tparam F an [[Applicative]]
    * @tparam A type of values of the Traversable
    * @tparam B type of values carried within the [[Applicative]]
    * @return an [[Applicative]] action applied to a [[Traversable]] of B's
    */
  def traverse[T[_]: Traversable, F[_]: Applicative, A, B](ta: T[A])(
      k: A => F[B]): F[T[B]] =
    implicitly[Traversable[T]].traverse(ta)(k)
}
