package com.victorromano.kafkaspringboot.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SampleKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleKafkaConsumer.class);

    @KafkaListener(topics = "myTopic1")
    public void handle(ConsumerRecord<String, String> consumerRecord) {
        LOGGER.info("Consumed message from myTopic1 with offset: {}, key: {}, value: {}",
                consumerRecord.offset(),
                consumerRecord.key(),
                consumerRecord.value());
    }

}
