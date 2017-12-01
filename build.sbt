import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "net.cakesolutions",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Cake Solutions Functional Programming Interview Questions",
    libraryDependencies += scalaTest % Test
  )
