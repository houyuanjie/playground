package fun.curried.config

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.{ConsumerFactory, DefaultKafkaConsumerFactory}

import java.util
import scala.compiletime.uninitialized

@Configuration
@EnableKafka
class KafkaConsumerConfig extends LazyLogging:

    @Value("${spring.kafka.bootstrap-servers}")
    private var bootstrapAddress: String = uninitialized

    @Value("${spring.kafka.consumer.group-id}")
    private var groupId: String = uninitialized

    @Bean
    def consumerFactory(): ConsumerFactory[String, String] =
        logger.info("初始化 consumerFactory")
        val configs = new util.HashMap[String, AnyRef]()
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        new DefaultKafkaConsumerFactory(configs)

    @Bean
    def kafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory[String, String]
    ): ConcurrentKafkaListenerContainerFactory[String, String] =
        logger.info("初始化 kafkaListenerContainerFactory")
        val factory = new ConcurrentKafkaListenerContainerFactory[String, String]
        factory.setConsumerFactory(consumerFactory)
        factory
