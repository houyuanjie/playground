package fun.curried.config

import com.typesafe.scalalogging.LazyLogging
import fun.curried.constant.{Groups, Topics}
import org.apache.kafka.clients.admin.{AdminClientConfig, NewTopic}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaAdmin

import java.util
import scala.compiletime.uninitialized

@Configuration
class KafkaTopicConfig extends LazyLogging:

    @Value("${spring.kafka.bootstrap-servers}")
    private var bootstrapAddress: String = uninitialized

    @Bean
    def kafkaAdmin(): KafkaAdmin =
        logger.info("初始化 kafkaAdmin")
        val config = new util.HashMap[String, AnyRef]
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        new KafkaAdmin(config)

    @Bean
    def testTopic(): NewTopic =
        logger.info("初始化 testTopic")
        new NewTopic(Topics.TEST_TOPIC, 1, 1.toShort)

    @KafkaListener(topics = Array(Topics.TEST_TOPIC), groupId = Groups.PUBSUB)
    def listenTestTopic(msg: String): Unit =
        logger.info(s"收到消息 topic=${Topics.TEST_TOPIC} groupId=${Groups.PUBSUB} msg=$msg")
