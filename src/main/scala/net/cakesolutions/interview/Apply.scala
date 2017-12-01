package net.cakesolutions.interview

/**
  * This is an Applicative semi-structure that we commonly factor out
  * due to its importance. All it does is allow you to define how
  * values of some type are inserted into a context.
  *
  * @tparam F The context to insert values into
  */
trait Apply[F[_]] {

  /**
    * Insert an `A` of some type into a context `F`
    * @return a value within some context
    */
  def pure[A](a: A): F[A]

}
