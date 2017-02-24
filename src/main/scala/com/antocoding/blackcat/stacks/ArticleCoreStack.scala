package com.antocoding.blackcat.stacks

import org.scalatra.{MethodOverride, ScalatraServlet}
import com.antocoding.blackcat.init.DatabaseSessionSupport

trait ArticleCoreStack extends ScalatraServlet with DatabaseSessionSupport with MethodOverride {

}
