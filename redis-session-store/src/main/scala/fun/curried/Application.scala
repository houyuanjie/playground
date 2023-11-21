package fun.curried

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.{ClassTagExtensions, DefaultScalaModule}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application:

    @Bean
    def jsonMapper: JsonMapper = JsonMapper
        .builder()
        .addModule(DefaultScalaModule)
        .build() :: ClassTagExtensions

@main
def run(args: String*) =
    SpringApplication.run(classOf[Application], args*)
