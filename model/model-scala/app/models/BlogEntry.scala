package models

import java.util.Date
// <import>
import scala.slick.driver.ExtendedProfile
import slick.session.{ Session => SlickSession }
import controllers.Store

import Store.driver.simple._
// </import>

// <model>
case class BlogEntry(
  authorId: Int,
  subject: String,
  text: String)
// </model>

// <model-auto-id>
case class Author(
    id : Option[Int],
    name: String,
    pwd: String) {
}
// </model-auto-id>

// <dao-reference>
object BlogEntries extends Table[BlogEntry]("BLOG_ENTRY") {
  def authorId = column[Int]("AUTH_ID")
  def subject = column[String]("SUBJECT")
  def text = column[String]("TEXT")
  def * = authorId ~ subject ~ text <> (BlogEntry, BlogEntry.unapply _)
  def author = foreignKey("AUTH_FK", authorId, Authors)(_.id)
}
// </dao-reference>

// <dao-auto-id> 
object Authors extends Table[Author]("AUTHOR") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def pwd = column[String]("PWD")
  def * = id.? ~ name ~ pwd <> (Author, Author.unapply _)
  def create = id.? ~ name ~ pwd <> (Author, Author.unapply _) returning id
}
// </dao-auto-id>

