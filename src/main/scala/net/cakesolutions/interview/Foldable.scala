package net.cakesolutions.interview

/**
  * A data structure that can be folded from left to right
  */
trait Foldable[F[_]] {

  /**
    * Evaluate a recursive data structure according to the
    * combinator `f`, which takes a value of the data structure
    * and combines it with other values in the structure. Fundamentally,
    * folds define the recursion scheme that defines the structure itself.
    *
    * In fact, if you have a fold, you can actually uniquely define
    * a recursive type if you can universally quantify over the type
    * paramaters `A` and `B` using something called its Church, Scott, or Perigot
    * encoding.
    *
    * See [[examples]] for an example of fold applied to Option, and Trees
    *
    * @param f - the function which is distributed throughout the structure,
    *          collapsing it by applying to function to some subset of elements
    *          in the list
    * @param z - a fixed point for the function (i.e. f(z) = z), which marks
    *          the termination of the recursion and function application
    * @param fa - some datastructure which is to be folded
    * @return the output of `f` applied to some subset of `A`'s in F[A]
    *
    *
    */
  def foldr[A, B](fa: F[A])(z: => B)(f: A => B => B): B

  /**
    * Foldmap is sometimes useful when we know the structure of the values of F
    * have a monoid instance. Note that one could define `foldr` in terms of
    * `foldMap` and vice versa. This is because Foldable structures are implicitly
    * monoidal - `foldr` naturally takes advantage of F's monoidal structure by
    * using the terminal case of `F` as the fixed point to mark recursive termination,
    * acting as an identity for the fold.
    *
    * @param f - a way of transforming elements of type A into their monoidal
    *          counterpart
    * @param fa - some structure containing types which naturally form
    *           a monoid under some operation.
    * @param m: A monoid which contains the action and fixed point for
    *         values of type M
    * @return
    */
  def foldMap[A, M](fa: F[A])(f: A => M)(implicit m: Monoid[M]): M =
    foldr(fa)(m.mzero)(a => mm => m.mappend(f(a))(mm))
}
