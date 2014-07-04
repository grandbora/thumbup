import com.typesafe.sbt.SbtStartScript

seq(SbtStartScript.startScriptForClassesSettings: _*)

name := "Thumbsup"

version := "0.1"

scalaVersion := "2.10.4"

resolvers += "twitter-repo" at "http://maven.twttr.com"

libraryDependencies ++= Seq()

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"  % "2.2.0" % "test",
  "com.twitter" %% "finagle-core" % "6.2.0",
  "com.twitter" %% "finagle-http" % "6.2.0"
)
