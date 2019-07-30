package com.victorromano.kafkaspringboot.kafka;

import com.victorromano.avro.kafkaspringboot.Key;
import com.victorromano.avro.kafkaspringboot.Value;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SampleKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleKafkaConsumer.class);

    @KafkaListener(topics = "myTopic2")
    public void handle(ConsumerRecord<Key, Value> consumerRecord) {
        LOGGER.info("Consumed message from myTopic2 with offset: {}, key: {}, value: {}",
                consumerRecord.offset(),
                consumerRecord.key().getKey(),
                consumerRecord.value().getMessage());
    }

}
