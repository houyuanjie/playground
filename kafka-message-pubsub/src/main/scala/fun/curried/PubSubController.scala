package fun.curried

import com.typesafe.scalalogging.LazyLogging
import fun.curried.constant.Topics
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, ResponseBody}

@Controller
class PubSubController(private val kafkaTemplate: KafkaTemplate[String, String]) extends LazyLogging:

    @GetMapping(path = Array("/send"))
    @ResponseBody
    def send(@RequestParam msg: String): String =
        logger.info(s"发送 $msg")
        kafkaTemplate
            .send(Topics.TEST_TOPIC, msg)
            .whenComplete: (result, e) =>
                e match
                    case null  => logger.info(s"成功发送 offset=${result.getRecordMetadata.offset()} msg=$msg")
                    case error => logger.error(s"发送失败 error=${error.getMessage} msg=$msg")
        "完成"
