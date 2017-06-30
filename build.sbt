name := "slick-sample-project"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "com.h2database" % "h2" % "1.4.196",
  "org.scalatest" %% "scalatest" % "3.0.3" % Test
)
