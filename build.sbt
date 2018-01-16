/**
  * The Cake Solutions FP interview standard build
  */

lazy val root = (project in file("."))
  .settings(name := "Cake Solutions FP Interview")
  .settings(organization := "net.cakesolutions")
  .settings(scalaVersion := "2.12.4")
  .settings(version := "0.1.0-SNAPSHOT")
  .aggregate(base, test)

lazy val base = (project in file("base"))
  .settings(moduleName := "Interview Base")
  .settings(commonSettings ++ publishSettings)

lazy val test = (project in file("test"))
  .settings(moduleName := "Interview Testing Suite")
  .settings(libraryDependencies ++= List(
    "org.scalatest" %% "scalatest" % "3.0.3" % Test)
  )
  .settings(commonSettings)
  .dependsOn(base)

lazy val commonSettings = scalacOptions ++= List(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-encoding",
  "utf-8", // Specify character encoding used by source files.
  "-explaintypes", // Explain type errors in more detail.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-deprecation",
  "-Xfuture",
  "-Xsource:2.12",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps",
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
  "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
  "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
  "-Xlint:unsound-match", // Pattern match may not be typesafe.
  "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ypartial-unification", // Enable partial unification in type constructor inference
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Ywarn-nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Ywarn-nullary-unit", // Warn when nullary methods return Unit.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
  "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
  "-Ywarn-value-discard"
)

lazy val publishSettings = List(
  resolvers ++= List(Resolver.sonatypeRepo("snapshots"),
    Resolver.sonatypeRepo("releases")),
  licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
)
