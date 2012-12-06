package controllers

import play.api.mvc._
import play.api.db.DB
import play.api.Play._
import play.Configuration
import slick.session.Database
import slick.session.{ Session => SlickSession }
import slick.driver.ExtendedProfile

// <Store>
object Store {
  lazy val driver = Configuration.root().getString("db.default.driver") match {
    case "org.h2.Driver" => scala.slick.driver.H2Driver
  }
}
// </Store>

// <Persistent>
trait Persistent {

  lazy val db = Database.forDataSource(DB.getDataSource())
  // </Persistent>
  
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
