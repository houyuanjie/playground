package fun.curried

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.{ClassTagExtensions, DefaultScalaModule}
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class BootScalaAutoConfiguration:

    @Bean
    @ConditionalOnClass(value = Array(classOf[DefaultScalaModule], classOf[ClassTagExtensions]))
    def scalaJsonMapper: JsonMapper = JsonMapper
        .builder()
        .addModule(DefaultScalaModule)
        .build() :: ClassTagExtensions
