package net.cakesolutions.interview

package object instances {


  implicit val listFunctor: Functor[List] = new Functor[List] {
    override def fmap[A, B](f: A => B)(fa: List[A]): List[B] =
      fa match {
        case a::as => f(a)::fmap(f)(as)
        case Nil => Nil
      }
  }

  implicit val listApply: Apply[List] = new Apply[List] {
    override def pure[A](a: A): List[A] = a :: Nil
  }

  implicit val listApplicative: Applicative[List] = new Applicative[List] {
    override def pure[A](a: A): List[A] = listApply.pure(a)
    override def fmap[A, B](f: A => B)(fa: List[A]): List[B] = listFunctor.fmap(f)(fa)
    override def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] =
      ff match {
        case f::fs => listFunctor.fmap(f)(fa) ::: ap(fs)(fa)
        case Nil => Nil
      }
  }

  implicit val listMonad: Monad[List] = new Monad[List] {
    override def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] = listApplicative.ap(ff)(fa)
    override def pure[A](a: A): List[A] = listApply.pure(a)
    override def fmap[A, B](f: A => B)(fa: List[A]): List[B] = listFunctor.fmap(f)(fa)

    override def join[A, B](mma: List[List[A]]): List[A] =
      mma match {
        case xx::xxs =>
          xx match {
            case x::xs =>
              x :: join(xs::xxs)
            case Nil => join(xxs)
          }
        case Nil => Nil
      }

    override def bind[A, B](ma: List[A])(k: A => List[B]): List[B] =
      join(fmap(k)(ma))

  }

  // TODO
  implicit val listFoldable: Foldable[List] = ???

  // TODO
  implicit val listTraversable: Traversable[List] = ???

}
