package com.antocoding.blackcat

import com.antocoding.blackcat.auth.AuthenticationSupport
import com.antocoding.blackcat.models.Article
import com.antocoding.blackcat.stacks.BrowserStack

class SessionsController extends BrowserStack with AuthenticationSupport {

  before("/new") {
    logger.info("SessionsController: checking whether to run RememberMeStrategy: " + !isAuthenticated)

    if(!isAuthenticated) {
      scentry.authenticate("RememberMe")
    }
  }

  get("/new") {
    if (isAuthenticated) redirect("/blog")

    contentType="text/html"
    ssp("/sessions/new", "allArticles" -> Article.all, "authenticated" -> isAuthenticated)
  }

  post("/") {
    scentry.authenticate()

    if (isAuthenticated) {
      redirect("/blog")
    }else{
      redirect("/sessions/new")
    }
  }

  /**
   * Any action that has side-effects on the server should not be a GET (a DELETE would
   * be preferable here), but I'm going to make this a GET in order to avoid starting a discussion
   * of unobtrusive JavaScript and the creation of DELETE links at this point.
   */
  get("/destroy") {
    scentry.logout()
    redirect("/blog")
  }

}
