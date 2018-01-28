val dottyVersion = "0.6.0-RC1"

lazy val root = (project in file(".")).settings(
  name := "dotty-frp",
  version := "0.1.0",
  scalaVersion := dottyVersion,
  libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
)
