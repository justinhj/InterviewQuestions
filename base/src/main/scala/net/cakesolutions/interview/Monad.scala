package net.cakesolutions.interview

/**
  * The infamous burrito.
  *
  *
  * The Monad class defines the basic operations over a monad, a concept
  * from a branch of mathematics known as category theory. From the perspective
  * of a Scala programmer, however, it is best to think of a monad as an abstract
  * datatype of actions. Scala's `for` comprehensions provide a convenient syntax
  * for writing monadic expressions.
  *
  * Instances of Monad should satisfy the following laws:
  *
  *   `Invariance of pure I`
  *     {{{pure a >>= k = k a}}}
  *   `Invariance of pure II`
  *     {{{m >>= return = m}}}
  *   `Equivalence lambda expressions`
  *     {{{m >>= (\x -> k x >>= h) = (m >>= k) >>= h}}}
  *
  */
trait Monad[M[_]] extends Applicative[M] {

  /**
    * Pronounced `>>=`, but more often, in scala, we call it "flatMap".
    *
    * @param ma - some container to be flatmapped
    * @param k - a Kleisli combinator; i.e. some function A -> M[B]
    *          which inserts values of type A into contexts of type B,
    *          transforming them. These form what we call "Kleisli Arrows"
    * @return some monadic context of B's
    */
  def bind[A, B](ma: M[A])(k: A => M[B]): M[B]

  /**
    * Alternatively, you can define Join: M[M[A]] => M[A] to complete the definition.
    * This is what's referred to as a "non-proper morphism", since it requires another
    * functional definition in order to implement proper morphic behavior of Monad
    *
    * It's just as powerful, however, and sometimes more convenient.
    *
    * In Scala, we call this "flatten"
    */
  def join[A, B](mma: M[M[A]]): M[A] =
    bind(mma)(identity)
}
