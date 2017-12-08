package net.cakesolutions.interview

/**
  * Class of data structures that can be traversed from left to right,
  * performing an action on each element.
  *
  * Note that Traversables are Foldable Functors, but many of their signatures
  * require some tighter constraint on the quality of Functors they interact with -
  * this does not mean they themselves are, for instance, Applicatives, or Monads,
  * only that a stronger structure is required to define methods like `traverse` or
  * `sequence`.
  *
  * A definition of traverse must satisfy the following laws:
  *
  *   `naturality:`
  *     {{{t . traverse f = traverse (t . f) for every applicative transformation t}}}
  *
  *   `identity`
  *     {{{traverse Identity = Identity}}}
  *
  *   `composition`
  *     {{{traverse (Compose . fmap g . f) = Compose . fmap (traverse g) . traverse f}}}
  *
  * A definition of sequenceA must satisfy the following laws:
  *
  *   `naturality`
  *     {{{t . sequenceA = sequenceA . fmap t for every applicative transformation t}}}
  *
  *
  *   `identity`
  *     {{{sequenceA . fmap Identity = Identity}}}
  *
  *   `composition`
  *     {{{sequenceA . fmap Compose = Compose . fmap sequenceA . sequenceA}}}
  *
  */
trait Traversable[T[_]] extends Functor[T] with Foldable[T] {

  /**
    * Insert each element of type `A` in `T` into an applicative context `F`,
    * evaluating these actions from left to right
    *
    * See the package [[examples]] for an example.
    *
    * @param k - the action that inserts an `A` of `T` into an applicative context `F`
    * @param ta - the traversable structure consisting of values of type `A`
    * @return an applicative context consisting of the evaluated traversable
    */
  def traverse[A, B, F[_]: Applicative](ta: T[A])(k: A => F[B]): F[T[B]]

  /**
    * Evaluate each action in some applicative context in the traversable structure from
    * left to right, and and collect the results.
    *
    * @param tfa - the traversable context for some applicative
    * @return a traversable of the collected results in an applicative context
    */
  def sequenceA[A, F[_]: Applicative](tfa: T[F[A]]): F[T[A]] =
    traverse(tfa)(identity)

}
