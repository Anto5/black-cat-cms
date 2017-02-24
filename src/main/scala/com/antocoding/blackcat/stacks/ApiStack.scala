package com.antocoding.blackcat.stacks

import org.scalatra.json.JacksonJsonSupport
import org.scalatra.swagger.JacksonSwaggerBase
import com.antocoding.blackcat.auth.ApiAuthenticationSupport
import org.json4s.DefaultFormats

trait ApiStack extends ArticleCoreStack with ApiAuthenticationSupport
  with JacksonJsonSupport {

  override protected implicit lazy val jsonFormats = DefaultFormats

}
