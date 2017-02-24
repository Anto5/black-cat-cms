package com.antocoding.blackcat


import com.antocoding.blackcat.auth.AuthenticationSupport
import com.antocoding.blackcat.models.Article
import com.antocoding.blackcat.stacks.BrowserStack
import javax.servlet.http.HttpServletRequest
import org.scalatra._

class BlogController extends BrowserStack with AuthenticationSupport {

  before() {
    contentType = "text/html"
  }

  /**
   * Show all blog entries
   */
  get("/") {
    ssp("/blog/index", "allArticles" -> Article.all, "authenticated" -> isAuthenticated)
  }

  /**
   * Show a specific article.
   */
  get("/:id") {
    val id = params.getAs[Int]("id").getOrElse(0)
    val article = Article.get(id)
    ssp("/blog/show", "article" -> article, "allArticles" -> Article.all, "authenticated" -> isAuthenticated)
  }

  /**
   * Display a form for creating a new articles.
   */
  get("/new") {
    requireLogin
    ssp("/blog/new", "allArticles" -> Article.all, "authenticated" -> isAuthenticated)
  }


  /**
   * Create a new article in the database.
   */
  post("/") {
    requireLogin
    val title = params("title")
    val content = params("""content""")
    val author = params("author")
    val date = params.getAs[Int]("date").getOrElse(
      halt(BadRequest("Please provide date.")))
   
	val articleUrl = s"http://localhost/blog/#/${Article.routeParser}"
    val article = new Article(0, title, content, author, date, articleUrl)

    if(Article.create(article)) {
      flash("notice") = "Articolo creato."
      redirect("/blog/" + article.id)

    }
  }

  
    get("/edit") {
	requireLogin
    ssp("/blog/index", "allArticles" -> Article.all, "authenticated" -> isAuthenticated)
    }
  

    get("/edit/:id") {
	requireLogin
    val id = params.getAs[Int]("id").getOrElse(0)
    val article = Article.get(id)
    ssp("/blog/editor", "article" -> article, "allArticles" -> Article.all, "authenticated" -> isAuthenticated)
  }
  
 
  post("/:id") {
      requireLogin
	  val id = params.getAs[Int]("id").getOrElse(0)
      var article = Article.get(id)
	  article.title = params("title")
      article.content = params("""content""")
	  Article.updateArticle(article)
	  redirect("/blog/" + article.id)
    }
  
 
}
 
 
 


