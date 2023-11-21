package fun.curried

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.{ClassTagExtensions, DefaultScalaModule}
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
@ConditionalOnClass(value =
    Array(
      classOf[Jdk8Module],
      classOf[JavaTimeModule],
      classOf[DefaultScalaModule],
      classOf[ClassTagExtensions]
    )
)
class JacksonJsonMapperAutoConfiguration:

    @Bean
    def customJsonMapper: JsonMapper = JsonMapper
        .builder()
        .addModule(new Jdk8Module)
        .addModule(new JavaTimeModule)
        .addModule(new DefaultScalaModule)
        .build() :: ClassTagExtensions
