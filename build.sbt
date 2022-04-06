val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "MADN",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
<<<<<<< HEAD
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"
  )
=======
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"
  )
>>>>>>> 4aeca100222c66db8e5efdf5b897be35534fea1a
