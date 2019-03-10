lazy val root = (project in file(".")).settings(
  name := "dotty-frp",
  version := "0.1.0",
  scalaVersion := "0.13.0-RC1",
  libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
)
