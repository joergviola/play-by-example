package controllers

import play.api._
import play.api.mvc._
import models._

// <Controller>
object Application extends Controller with Persistent {

  def index = TransAction { request =>
    implicit session =>
      import Store.driver.simple._
// </Controller>
// <Insert>
      val authId = Authors.create.insert(Author(None, "Joerg", "869867"))
      BlogEntries.insert(BlogEntry(authId, "s1", "t1"))
      BlogEntries.insert(BlogEntry(authId, "s2", "t2"))
// </Insert>
// <Query>
      val entries = for {
        b <- BlogEntries
        a <- b.author
      } yield (a,b)
      Ok(views.html.index("Your new application is ready.", entries.list))
// </Query>
  }

}
