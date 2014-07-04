import com.typesafe.sbt.SbtStartScript

seq(SbtStartScript.startScriptForClassesSettings: _*)

name := "Thumbsup"

version := "0.1"

scalaVersion := "2.10.4"

resolvers += "twitter-repo" at "http://maven.twttr.com"

libraryDependencies ++= Seq()

libraryDependencies ++= Seq(
 "com.twitter" %% "finatra" % "1.5.3",
 "org.scalikejdbc" %% "scalikejdbc"       % "2.0.4",
  "com.h2database"  %  "h2"                % "1.4.179",
  "ch.qos.logback"  %  "logback-classic"   % "1.1.2"
)
