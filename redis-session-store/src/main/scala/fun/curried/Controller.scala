package fun.curried

import com.typesafe.scalalogging.LazyLogging
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class Controller extends LazyLogging:

    @GetMapping(path = Array("/hello"))
    def hello(): Map[String, Any] =
        logger.info("访问 /hello")
        Map(
          "msg"     -> "Hello",
          "success" -> true
        )
