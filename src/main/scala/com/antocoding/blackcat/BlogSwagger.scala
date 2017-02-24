package com.antocoding.blackcat

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{JacksonSwaggerBase, Swagger}

class BlogSwagger(implicit val swagger: Swagger)
  extends ScalatraServlet with JacksonSwaggerBase