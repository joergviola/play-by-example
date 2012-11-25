package controllers

import play.api._
import play.api.mvc._
import models.BlogEntry

object Application extends Controller with Persistent {

  def index = TransAction { request =>
    implicit session =>
      import driver.simple._
      blogEntries.insert(BlogEntry("s1", "t1"))
      val entries = for (b <- blogEntries) yield b
      Ok(views.html.index("Your new application is ready.", entries.list))
  }

}