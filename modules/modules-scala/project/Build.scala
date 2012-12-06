import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "modules-scala"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
	  "org.webjars" % "webjars-play" % "2.1-RC1",
	  "org.webjars" % "bootstrap" % "2.2.1"
  )


  val pages = play.Project(appName+"-pages", appVersion, appDependencies, path = file("modules/pages")).settings(
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
  ).dependsOn(pages)

}
