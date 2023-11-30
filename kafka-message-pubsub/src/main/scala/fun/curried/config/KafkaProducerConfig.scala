package fun.curried.config

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.kafka.core.{DefaultKafkaProducerFactory, KafkaTemplate, ProducerFactory}

import java.util
import scala.compiletime.uninitialized

@Configuration
class KafkaProducerConfig extends LazyLogging:

    @Value("${spring.kafka.bootstrap-servers}")
    private var bootstrapAddress: String = uninitialized

    @Bean
    def producerFactory(): ProducerFactory[String, String] =
        logger.info("初始化 producerFactory")
        val configs = new util.HashMap[String, AnyRef]()
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
        new DefaultKafkaProducerFactory(configs)

    @Bean
    def kafkaTemplate(producerFactory: ProducerFactory[String, String]): KafkaTemplate[String, String] =
        logger.info("初始化 kafkaTemplate")
        new KafkaTemplate[String, String](producerFactory)
