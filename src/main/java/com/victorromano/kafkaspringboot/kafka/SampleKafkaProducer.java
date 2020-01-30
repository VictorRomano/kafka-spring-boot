package com.victorromano.kafkaspringboot.kafka;

import com.victorromano.avro.kafkaspringboot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleKafkaProducer {
    private KafkaTemplate<String, Value>  kafkaTemplate;

    @Autowired
    public SampleKafkaProducer(KafkaTemplate<String, Value> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String send(String key, String message) {
        return send(key, message, "myTopic2");
    }

    public String send(String key, String message, String topic) {
        Value avroMessage = Value.newBuilder()
                .setMessage(message)
                .build();

        kafkaTemplate.send(topic, key, avroMessage);
        return message;
    }

}
