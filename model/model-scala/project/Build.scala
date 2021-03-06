import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "play-intro"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.typesafe" % "slick_2.10.0-RC1" % "0.11.2", // use the right version here
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.h2database" % "h2" % "1.3.166",
    jdbc)

  val main = play.Project(appName, appVersion, appDependencies).settings( // Add your own project settings here      
  )

}
