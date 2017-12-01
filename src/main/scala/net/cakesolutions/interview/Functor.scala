package net.cakesolutions.interview

/**
  * Functors are the single most ubiquitous concenpt in functional program
  *
  * They are container types that can transform their contents according to
  * some function `f`. On a more abstract level, they are contexts in which
  * functions may be applied to objects to get arrows A -> B, and lift
  * functions between objects to functions between a container for those
  * objects.
  *
  * They must obey a few simple laws:
  *
  *   `identity`
  *     fmap id == id fmap - note: not a two-sided inverse -
  *       the id maps are id for the corresponding domain/codomain type
  *   `composition`
  *     (f . g) == fmap f . fmap g
  *
  */
trait Functor[F[_]] {

  /**
    * Transform values of type A into values of type B within
    * some context.
    */
  def fmap[A, B](f: A => B)(fa: F[A]): F[B]

}
