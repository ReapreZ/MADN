/*val scala3Version = "3.1.1"

ThisBuild / scalaVersion := scala3Version

lazy val commonSettings = Seq(
    name := "MADN",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11",
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.5.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.8.0",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.10.0-RC5"),
    //jacocoExcludes := Seq("*aview*", "*util*", "*controller*"),
    //coverageExcludePackages := "C:/Software-Engineering/MADN/src/main/scala/controller",
    //coverageExcludeFiles := "C:/Software-Engineering/MADN/src/main/scala/MADN.scala"
  )
lazy val util = (project in file("util"))
  .settings(
      name := "MADN-UTIL",
      version := "0.1.0-SNAPSHOT",
      commonSettings
  )*/