import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "net.cakesolutions",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    name := "interview"
  )

lazy val commonSettings = List(
  scalacOptions ++= List(
    "-deprecation",
    "-unchecked",
    "-feature",
    "-encoding",
    "UTF-8",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Xfuture",
    "-language:postfixOps"
  ),
  javaOptions in run ++= List(
    "-Xms512M",
    "-Xmx2G",
    "-Xss8M",
    "-XX:+UseG1GC"
  ),
  fork in run := true,
  resolvers ++= List(Resolver.sonatypeRepo("snapshots"),
    Resolver.sonatypeRepo("releases")),
  initialCommands in console :=
    "import " +
      "net.cakesolutions.interview._, " +
      "net.cakesolutions.interview.instances._, "
  )