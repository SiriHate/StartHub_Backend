package org.siri_hate.notification_service.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for the configuration of Kafka consumers in the application.
 * It uses the @EnableKafka and @Configuration annotations to indicate that it is a configuration class and that it enables Kafka.
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    /**
     * The address of the Kafka bootstrap server.
     */
    @Value("${bootstrap.address}")
    private String bootstrapAddress;

    /**
     * The group ID for the Kafka consumer.
     */
    @Value("${group.id}")
    private String groupId;

    /**
     * This method creates a ConsumerFactory that is used to create Kafka consumers.
     * It configures the consumers with the bootstrap server address, group ID, and deserializer classes.
     * It also configures the consumers to reconnect in case of a connection loss.
     *
     * @return a ConsumerFactory for creating Kafka consumers
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, 10000);
        configProps.put(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, 10000);
        configProps.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    /**
     * This method creates a ConcurrentKafkaListenerContainerFactory that is used to create Kafka listeners.
     * It sets the ConsumerFactory for the listeners to the one created by the consumerFactory() method.
     *
     * @return a ConcurrentKafkaListenerContainerFactory for creating Kafka listeners
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}