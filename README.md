# Cake Solutions Interview -

The object of this offline quiz is to test your problem solving abilities within a purely functional programming context. You've been given a small subset of the standard typeclass hierarchy, with the standard methods defined in each, from Functor up through Monad and Traversable, as well as examples of each instance given in the `examples` package.

Take your time studying the collection of typeclasses given in the `interview` package, and, when you're ready, attempt to implement `Traversable` and `Foldable` instances for Scala's standard `List` data type (found in the `instances` package). You've been given instances of each of the other typeclasses already to build upon, and it's up to you to choose which instances/functionality you'll bring in in order to accomplish the task.

Implementing `Traversable` and `Foldable` comes down to implementing 3 methods, for which you've been given the signatures:

- `foldr` - this is the standard `foldRight` that we see in Scala every day.
- `traverse` - a very common method which applies an action `A => G[B]` to some `F[A]` when `G` is an applicative
- `sequenceA` - which uses an applicative functor to turn `F[G[A]]` into `G[F[A]]` when `G` is an applicative


You will be graded on code cleanliness, as well as completeness. It's not required, but tests would be nice as well, for your own sake. Feel free to use any resources at your disposal, aside from having other people do it for you, and try your best!


*Note: Please do not use the standard API functions, such as List.foldRight in your implementations*
