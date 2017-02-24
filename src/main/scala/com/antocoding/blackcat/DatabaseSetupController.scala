package com.antocoding.blackcat

import com.antocoding.blackcat.auth.OurBasicAuthenticationSupport
import com.antocoding.blackcat.models.Db
import com.antocoding.blackcat.stacks.BrowserStack

class DatabaseSetupController extends BrowserStack with OurBasicAuthenticationSupport {


  before() {
    contentType = "text/html"
    basicAuth
  }

  /**
   * Create a new database. The route is "database/create"
   */
  get("/create") {
    Db.create
    flash("notice") = "Database created!"
    redirect("/blog/new")
  }

  /** *
    * Print the schema definition for our database out to the console.
    */
  get("/dump-schema") {
    Db.printDdl
    "Take a look in your running console and you'll see the data definition list printout."
  }

  /**
   * Whack the database.
   */
  get("/drop") {
    Db.drop
    "The database has been dropped."
  }
}
