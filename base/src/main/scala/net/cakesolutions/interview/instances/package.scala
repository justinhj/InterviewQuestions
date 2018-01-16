package net.cakesolutions.interview

package object instances {

  // TODO: This is where you should define your work

  /**
    * TODO: Define Functor for List
    */
  implicit val listFunctor: Functor[List] = ???

  /**
    * TODO: Define Foldable for List
    */
  implicit val listFoldable: Foldable[List] = ???

  /**
    * TODO: Define Traversable for List.
    *
    */
  implicit val listTraversable: Traversable[List] = ???

}
