package fun.curried

import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class Controller:

    @GetMapping(path = Array("/hello"))
    def hello: Map[String, Any] = Map(
      "msg"     -> "Hello",
      "success" -> true
    )
