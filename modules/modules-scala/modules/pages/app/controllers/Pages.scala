package controllers.pages

import play.api._
import play.api.mvc._

object Pages extends Controller {
  
  def terms = Action {
    Ok(views.html.pages.terms("hi there"))
  }
  
  def terms2 = Action {
    Ok(views.html.pages.terms("hi there 2"))
  }
  
}