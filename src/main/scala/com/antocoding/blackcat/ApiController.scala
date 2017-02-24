package com.antocoding.blackcat

import scala.Some
import java.net.URL
import org.squeryl.PrimitiveTypeMode._
import java.nio.charset.StandardCharsets
import com.antocoding.blackcat.models.Article
import com.antocoding.blackcat.stacks.ApiStack
import org.scalatra.BadRequest
import javax.servlet.http.HttpServletRequest
import org.scalatra.swagger._
import java.util.Random
import java.util.Collections
import com.antocoding.blackcat.auth.ApiAuthenticationSupport


class ApiController()(implicit val swagger: Swagger) extends ApiStack with SwaggerSupport {

  before() {
    contentType = formats("json")
    //validateRequest()
  }

  // Identifies the controller to swagger
  protected val applicationDescription =
    """Blog CMS"""

  val listArticles = (apiOperation[List[Article]]("listArticles")
    summary("Show all articles")
    notes("Shows all published articles."))

  get("/", operation(listArticles)) {
    Article.all.toList

       
  }


  val getArticle = (apiOperation[Article]("getArticle")
    summary("Retrieve a single article by id")
    notes("Foo")
    parameters(
    Parameter("id", DataType.Int, Some("The article's database id"), None, ParamType.Path, required = true)
    ))

  get("/:id", operation(getArticle)) {
    val id = params.getAs[Int]("id").getOrElse(0)
    Article.get(id)           
  }

  
/*
*  
*  val createArticle = (apiOperation[Article]("createArticle")
*    summary("Create a new article")
*    notes("title, content, author, and date are required")
*    parameters(
*    Parameter("title", DataType.String, Some("The article's title"), None, ParamType.Body, required = true),
*    Parameter("""content""", DataType.String, Some("The content"), None, ParamType.Body, required = true),
*     Parameter("author", DataType.String, Some("Who wrote the article"), None, ParamType.Body, required = t**rue),
*    Parameter("date", DataType.Int, Some("The date of publishing"),
*      Some("A four-digit number"), ParamType.Body, required = true)
*	//Parameter("articleUrl", DataType.String, Some("the url of the article"), None, ParamType.Body, required = false)
*	  )
*    )
*
*  post("/", operation(createArticle)) {
*    val title = params("title")
*    val content = params("""content""")
*    val author = params("author")
*    val date = params.getAs[Int]("date").getOrElse(
*    halt(BadRequest("Please provide a year of birth.")))
*	
*	val articleUrl = s"http://localhost/blog/#/${Article.routeParser}"
*	 
*    val article = new Article(0, title, content, author, date, articleUrl)
*    if(Article.create(article)) {
*    article	 
*    }
*  }
*
*  delete("/:id") {
*    val id = params.getAs[Long]("id").getOrElse(
*      halt(BadRequest("Please provide an id to destroy"))
*    )
*    Article.destroy(id)
*  }
*  
*    val updateArticle = (apiOperation[Article]("updateArticle")
*    summary("Update an article")
*    notes("update title and content")
*    parameters(
*	Parameter("id", DataType.Int, Some("The article's database id"), None, ParamType.Path, required = true),
*    Parameter("title", DataType.String, Some("The article's title"), None, ParamType.Body, required = true),
*    Parameter("""content""", DataType.String, Some("The content"), None, ParamType.Body, required = true)
*	  )
*    )
*  
*    put("/:id", operation(updateArticle)) {
* 	  val id = params.getAs[Int]("id").getOrElse(0)
*      var article = Article.get(id)
*	  article.title = params("title")
*      article.content = params("""content""")
*	  Article.updateArticle(article)
*    }
*  
*  
*/
}




