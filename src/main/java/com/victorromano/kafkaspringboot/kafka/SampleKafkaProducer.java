package com.victorromano.kafkaspringboot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleKafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public SampleKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String send(String key, String message) {
        kafkaTemplate.send("myTopic1", key, message);
        return message;
    }

}
