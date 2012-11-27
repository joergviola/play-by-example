package controllers

import play.api.mvc._
import play.api.db.DB
import play.api.Play._
import models.BlogEntryDAO
import play.Configuration
import slick.session.Database
import slick.session.{ Session => SlickSession }
import slick.driver.ExtendedProfile

// <DriverDefinition>
trait Persistent {
  def driverClass = Configuration.root().getString("db.default.driver") match {
    case "org.h2.Driver" => "scala.slick.driver.H2Driver"
  }
  // </DriverDefinition>

  // <DriverClass>
  val driver = singleton[ExtendedProfile](driverClass)

  private def singleton[T](name: String)(implicit man: Manifest[T]): T =
    Class.forName(name + "$").getField("MODULE$").get(man.runtimeClass).asInstanceOf[T]
  // </DriverClass>

  // <DAOs>
  def blogEntries = new BlogEntryDAO(driver).BlogEntries
  // </DAOs>

  // <DB>
  lazy val db = Database.forDataSource(DB.getDataSource())
  // </DB>

  // <TransAction>
  def TransAction(f: (Request[AnyContent]) => (SlickSession) => Result): Action[AnyContent] = {
    Action { request =>
      val session = db.createSession
      session withTransaction {
        f(request)(session)
      }
    }
  }
  // </TransAction>
}