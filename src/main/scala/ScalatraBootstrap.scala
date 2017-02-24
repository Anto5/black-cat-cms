import com.antocoding.blackcat._
import com.antocoding.blackcat.init.DatabaseInit
import javax.servlet.ServletContext
import org.scalatra.LifeCycle
import org.scalatra.swagger.{ApiInfo, Swagger}

class ScalatraBootstrap extends LifeCycle with DatabaseInit {

   implicit val apiInfo = new ApiInfo(
    "BlackCat CMS ",
    "Docs for the CMS",
    "http://www.antocoding.com",
    "blackcat@antocoding",
    "MIT",
    "http://opensource.org/licenses/MIT")

  implicit val swagger = new Swagger("1.2", "1.0.0", apiInfo)

  override def init(context: ServletContext) {
    configureDb()
    context.mount(new ApiController, "/blog-api", "blog-api")
    context.mount(new BlogSwagger, "/api-docs")
    context.mount(new DatabaseSetupController, "/database")
    context.mount(new BlogController, "/blog")
    context.mount(new SessionsController, "/sessions")
  }

  override def destroy(context: ServletContext) {
    closeDbConnection()
  }
}
