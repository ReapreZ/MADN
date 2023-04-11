val scala3Version = "3.1.1"

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
    jacocoReportSettings := JacocoReportSettings(
        "Jacoco Coverage Report",
        None,
        JacocoThresholds(),
        Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML), // note XML formatter
        "utf-8"),
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.10.0-RC5")
    //jacocoExcludes := Seq("*aview*", "*Dame*", "*util*", "*controller*"),
    //coverageExcludePackages := "C:/Software-Engineering/MADN/src/main/scala/controller",
    //coverageExcludeFiles := "C:/Software-Engineering/MADN/src/main/scala/MADN.scala"
)

lazy val root = (project in file("."))
  .aggregate(gui,tui,model)
  .dependsOn(gui,tui,util)
  .settings(
    name := "MADN-ROOT",
    version := "0.1.0-SNAPSHOT",
    commonSettings
  )

lazy val gui = (project in file("gui"))
  .dependsOn(model)
  .aggregate(model)
  .settings(
      name := "MADN-GUI",
      version := "0.1.0-SNAPSHOT",
      commonSettings
  )
lazy val tui = (project in file("tuiComponent"))
  .dependsOn(model)
  .aggregate(model)
  .settings(
      name := "MADN-TUI",
      version := "0.1.0-SNAPSHOT",
      commonSettings
  )
lazy val model = (project in file("model"))
  .settings(
      name := "MADN-MODEL",
      version := "0.1.0-SNAPSHOT",
      commonSettings
  )
lazy val controller = (project in file("controller"))
  .dependsOn(model, util)
  .aggregate(model)
  .settings(
    name := "MADN-CONTROLLER",
    version := "0.1.0-SNAPSHOT",
    commonSettings
  )
lazy val util = (project in file("util"))
  .dependsOn(model)
  .aggregate(model)
  .settings(
    name := "MADN-UTIL",
    version := "0.1.0-SNAPSHOT",
    commonSettings
  )
