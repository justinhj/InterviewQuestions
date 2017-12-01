package net.cakesolutions.interview

/**
  * A functor with application, providing operations to embed
  * pure expressions (pure, via `Apply`), and sequence computations
  * and combine their results (<*> i.e. ap, and liftA2).
  *
  * Further, any definition must satisfy the following:
  *
  *   `identity`
  *     {{{pure id <*> v = v}}}
  *   `composition`
  *     {{{pure (.) <*> u <*> v <*> w = u <*> (v <*> w)}}}
  *   `homomorphism`
  *     {{{pure f <*> pure x = pure (f x)}}}
  *   `interchange`
  *     {{{u <*> pure y = pure ($ y) <*> u}}}
  *
  * @tparam F
  */
trait Applicative[F[_]] extends Functor[F] with Apply[F] {

  /**
    * Sometimes called `<*>`, ap defines sequential application.
    * A few functors support an implementation of <*> that is more
    * efficient than the default one.
    *
    * @param ff - sequence of actions to apply
    * @param fa - collection of values to be acted upon
    * @return - a collection of values acted upon, where
    *         each entry is the result of the application of
    *         some action applied to the collection `fa`
    */
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

  /**
    * Lift a function of values to a function of contexts via partial application -
    * i.e. liftA f: A => B <=> F(f): F[A] -> F[B].
    *
    * Technically, you can do this with just Functor.
    *
    * @param f - the function to be lifted
    * @return the lifted function
    */
  def liftA[A, B](f: A => B): F[A] => F[B] = fmap(f) _

  /**
    * Applicative can be defined up to isomorphism via `ap` or `liftA2`. If Applicative
    * chooses to be `liftA2`-biased, then must satisfy an additional law:
    *
    * ap = liftA2 id liftA2 f x y = f `fmap` x `ap` y
    *
    * @param f the binary operation acting on A's and B's
    * @param fa - applicative container of A's
    * @param fb - applicative container of B's
    * @return - the application of the binary operation
    *         `f` combining elements of `fa` and `fb`
    *         using the action of `f` to produce an applicative
    *         context of C's
    */
  def liftA2[A, B, C](f: A => B => C)(fa: F[A])(fb: F[B]): F[C] =
    ap(fmap(f)(fa))(fb)

}
