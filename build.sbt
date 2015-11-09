name := "slick-sample-project"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.1.0",
  "com.h2database" % "h2" % "1.4.190",
  "org.scalatest" %% "scalatest" % "2.2.4" % Test
)
