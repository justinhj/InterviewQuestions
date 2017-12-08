# Cake Solutions Interview -

The object of this offline quiz is to test your problem solving abilities within a purely functional programming context. You've been given a small subset of the standard typeclass hierarchy, with the standard methods defined in each, from Functor up through Monad and Traversable, as well as examples of each instance given in the `examples` package.

Take your time studying the collection of typeclasses given in the `interview` package, and, when you're ready, attempt to implement `Functor`, `Traversable`, and `Foldable` instances for Scala's standard `List` data type (found in the `instances` package). It's up to you which instances/functionality you'll bring in in order to accomplish the task.

Implementing `Functor`, `Traversable`, and `Foldable` comes down to implementing 3 methods, for which you've been given the signatures:

- `fmap` - the standard "map" function that defines the functor
- `foldr` - this is the standard `foldRight` that we see in Scala every day.
- `traverse` - a very common method which applies an action `A => G[B]` to some `F[A]` when `G` is an applicative


You will be graded on code cleanliness, as well as completeness. Once you have a working implementation, we would like to see test code that proves your solution is sane and valid in the `test` directory. You've been provided `scalatest` for the task. Feel free to use any resources at your disposal, aside from having other people do it for you, and try your best!


*Note: Please do not use the standard API functions, such as List.foldRight in your implementations*
**Double Note: If you prefer the Scaladoc, feel free to do `sbt doc` at the root of the project to generate the scaladocs. Everything has been liberally commented to make sure you have a total explanation for how things are meant to operate, and how to think about them.
