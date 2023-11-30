package fun.curried

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

@main
def run(args: String*) =
    SpringApplication.run(classOf[Application], args*)
