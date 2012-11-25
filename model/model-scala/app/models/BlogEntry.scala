package models

import java.util.Date
import scala.slick.driver.H2Driver.simple._
import scala.slick.driver.ExtendedProfile

// <model>
case class BlogEntry(
  subject: String,
  text: String)
// </model>

// <dao>
class BlogEntryDAO(val driver: ExtendedProfile) {
  import driver.simple._
  object BlogEntries extends Table[BlogEntry]("BLOG_ENTRY") {
    def subject = column[String]("SUBJECT")
    def text = column[String]("TEXT")
    def * = subject ~ text <> (BlogEntry, BlogEntry.unapply _)
  }
}
// </dao>

