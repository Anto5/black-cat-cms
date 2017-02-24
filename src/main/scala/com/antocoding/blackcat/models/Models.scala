package com.antocoding.blackcat.models

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
import org.squeryl.Query
import org.squeryl.KeyedEntity
import org.squeryl.PersistenceStatus

case class User(val id: String) {
  def forgetMe() = {
    println("Destroying token in datastore")
  }
}

/**
 * A blog's article.
 */
case class Article(val id: Long, var title: String, var content: String, var author: String, val date: Int, val articleUrl : String) extends SquerylRecord {
  def this() = this(0, "Foo", "McBar", "It's better to ask forgiveness than permission", 1950 , "http:\\antocoding.com")

  }



/**
 * The BlogDb object acts as a cross between a Dao and a Schema definition file.
 */
object Db extends Schema {

  def init = {
    inTransaction {
      Db.create
    }
  }

  val blog = table[Article]("blog")
  on(blog)(a => declare(
    a.id is (autoIncremented)))
}

object Article {
  def create(article: Article): Boolean = {
  
    inTransaction {
      val result = Db.blog.insert(article)
      if (result.isPersisted) {
        true
      } else {
        false
      }
    }
  }
  
  
  
   def routeParser :Long = {
   from(Db.blog) (t =>compute(max(t.id))).getOrElse(0l) + 1l  
  }
  
  def updateArticle(article: Article) {
     
 Db.blog.update(article)

  }
  def all = {
    from(Db.blog)(select(_))
  }

  def get(id: Long) = {
    Db.blog.where(h => h.id === id).single
  }

  def destroy(id: Long) = {
    Db.blog.delete(id)
  }
}

/**
 * This trait is just a way to aggregate our model style across multiple
 * models so that we have a single point of change if we want to add
 * anything to our model behaviour
 */
trait SquerylRecord extends KeyedEntity[Long] with PersistenceStatus {

}