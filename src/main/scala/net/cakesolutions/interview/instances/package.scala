package net.cakesolutions.interview

package object instances {

  // TODO: This is where you should define your work

  /**
    * TODO: Define Foldable for List
    */
  implicit val listFoldable: Foldable[List] = ???

  /**
    * TODO: Define Traversable for List. You will need to define one more
    * typeclass instance to do this one. Look at the signature for
    * Traversable.
    *
    */
  implicit val listTraversable: Traversable[List] = ???

}
