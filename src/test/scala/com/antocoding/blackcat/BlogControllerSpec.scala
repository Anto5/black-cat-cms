package com.antocoding.blackcat

import com.antocoding.blackcat.init.DatabaseInit
import com.antocoding.blackcat.models.Db
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSuite}
import org.scalatra.test.scalatest.ScalatraSuite

class HackersControllerSpec extends FunSuite with ScalatraSuite with DatabaseInit with BeforeAndAfter with BeforeAndAfterAll {

  addServlet(classOf[BlogController], "/*")

  before {
    configureDb()
    Db.init
  }

  after {
    closeDbConnection()
  }

  test("simple get") {
    get("/new") {
      status should equal(302) // It's protected. Redirect!
    }
  }
}
