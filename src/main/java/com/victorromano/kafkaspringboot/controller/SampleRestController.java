package com.victorromano.kafkaspringboot.controller;

import com.victorromano.kafkaspringboot.kafka.SampleKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SampleRestController {

    private final SampleKafkaProducer producer;

    @Autowired
    public SampleRestController(SampleKafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/sample")
    public String sample() {
        String uuid = UUID.randomUUID().toString();
        return producer.send(uuid, uuid);
    }

    @PostMapping("/message/{topic}")
    public String message(@RequestBody MessageDto messageDto, @PathVariable String topic) {
        return producer.send(messageDto.getMessage(), messageDto.getMessage(), topic);
    }

}
