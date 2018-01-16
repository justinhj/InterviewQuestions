package net.cakesolutions.interview

import org.scalatest.{Matchers, WordSpec}

class FunctorSpec extends WordSpec with Matchers {
  import instances._

  def _fmap[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] =
    implicitly[Functor[F]].fmap(fa)(f)

  "Functors" should {

    "return true for the following" in {

      val simpleList: List[Int] = List(1, 2, 3)

      _fmap(simpleList)(_ * 3) shouldBe List(3, 6, 9)
    }
  }
}
