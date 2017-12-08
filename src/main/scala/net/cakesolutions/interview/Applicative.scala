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
trait Applicative[F[_]] extends Functor[F] {

  /**
    * Insert an `A` of some type into a context `F`
    * @return a value within some context
    */
  def pure[A](a: A): F[A]

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
  def ap[A, B](fa: F[A])(ff: F[A => B]): F[B]

  /**
    * Lift a function of values to a function of contexts via partial application -
    * i.e. liftA f: A => B <=> F(f): F[A] -> F[B].
    *
    * Technically, you can do this with just Functor.
    *
    * @param f - the function to be lifted
    * @return the lifted function
    */
  def liftA[A, B](f: A => B): F[A] => F[B] = fmap[A, B](_)(f)

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
  def liftA2[A, B, C](fa: F[A])(fb: F[B])(f: A => B => C): F[C] =
    ap(fb)(fmap(fa)(f))

  /**
    * `liftA3` is exactly like `liftA2`, except with an extra parameter. You can keep going,
    * but for my (and your) sake, I'll stop here. You can define liftAN inductively. Notice
    * that `liftA` and `liftA2` are your base cases, with `liftA2` = fb <*> (fmap fa f), and that
    * `liftA2` = fb <*> liftA. Proceed from there. You have for {fa_n}_{n \in I}, that
    * `liftAN` = fa_n <*> liftA(N-1) where I is some countable, partially ordered set.
    *
    * We define this for convenience in the Tree example for traversable. You, however, will
    * not need this. You might, however, need `liftA2` *hint hint hint*. It's up to you.
    *
    */
  def liftA3[A, B, C, D](fa: F[A])(fb: F[B])(fc: F[C])(
      f: A => B => C => D): F[D] =
    ap(fc)(liftA2(fa)(fb)(f))

}
