val scala3Version = "2.13.12"

ThisBuild / scalaVersion := scala3Version

lazy val commonSettings = Seq(
    name := "MADN",
    version := "0.1.0-SNAPSHOT",

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16",
    libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test",
    libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.10.0-RC5")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.typesafe.akka" %% "akka-actor-typed" % "2.8.0")
    .cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.typesafe.akka" %% "akka-http" % "10.5.0")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.typesafe.akka" %% "akka-stream" % "2.8.0")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.typesafe.slick" %% "slick" % "3.5.0-M3")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.typesafe.slick" %% "slick-hikaricp" % "3.5.0-M3")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.postgresql" % "postgresql" % "42.3.4",
    libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
    libraryDependencies += ("org.mongodb.scala" %% "mongo-scala-driver" % "4.9.0").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.9.5" exclude("com.typesafe.scala-logging", "scala-logging_2.13"),
    libraryDependencies += "io.gatling" % "gatling-test-framework" % "3.9.5" exclude("com.typesafe.scala-logging", "scala-logging_2.13"),
    jacocoReportSettings := JacocoReportSettings(
    "Jacoco Coverage Report",
    None,
    JacocoThresholds(),
    Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML), // note XML formatter
    "utf-8"),
    //jacocoExcludes := Seq("*aview*", "*util*", "*controller*"),
    //coverageExcludePackages := "C:/Software-Engineering/MADN/src/main/scala/controller",
    //coverageExcludeFiles := "C:/Software-Engineering/MADN/src/main/scala/MADN.scala"
  )


lazy val root = (project in file(""))
  .dependsOn(model, tui, gui, controller, util, rest, io)
  .aggregate(model, tui, gui, controller, util, rest, io)
  .settings(
      name := "MADN-ROOT",
      version := "0.1.0-SNAPSHOT",
      commonSettings
  )

lazy val gui = (project in file("gui"))
  .dependsOn(model,util, controller)
  .aggregate(model)
  .settings(
      name := "MADN-GUI",
      version := "0.1.0-SNAPSHOT",
      commonSettings
  )
lazy val tui = (project in file("tui"))
  .dependsOn(model, util, controller)
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
  .dependsOn(model, util, io)
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
lazy val rest = (project in file("rest"))
  .dependsOn(model, controller, util, tui)
  .aggregate(model, util, controller, tui)
  .enablePlugins(GatlingPlugin)
  .settings(
    name := "MADN-REST",
    version := "0.1.0-SNAPSHOT",
  )
lazy val io = (project in file("io"))
  .dependsOn(model)
  .aggregate(model)
  .settings(
    name := "MADN-IO",
    version := "0.1.0-SNAPSHOT",
    commonSettings
  )  