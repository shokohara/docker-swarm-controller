import play.sbt.PlayImport.PlayKeys._

name := "docker-swarm-controller"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-ec2" % "1.10.55",
  "org.typelevel" %% "cats" % "0.4.1"
)

maintainer in Docker := "Sho Kohara <skohar@users.noreply.github.com>"

dockerExposedPorts in Docker := Seq(9000)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
