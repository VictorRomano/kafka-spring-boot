package com.victorromano.kafkaspringboot.streams;

import com.victorromano.avro.kafkaspringboot.Key;
import com.victorromano.avro.kafkaspringboot.Value;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

@Component
@EnableKafkaStreams
public class SampleKafkaStreams {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleKafkaStreams.class);

    @Autowired
    @Qualifier("primaryKafkaStreamsBuilder")
    private StreamsBuilder primaryStreamsBuilder;

    @Autowired
    @Qualifier("secondaryKafkaStreamsBuilder")
    private StreamsBuilder secondaryStreamsBuilder;

    @Bean
    public KStream<Key, Value> testStream() {
        KStream<Key, Value> stream = primaryStreamsBuilder.stream("myTopic2");
        stream
                .peek((key, value) -> LOGGER.info("KStream-A Streaming message '{}'", value.getMessage()))
                .filter((key, value) -> value.getMessage().contains("a"))
                .peek((key, value) -> LOGGER.info("KStream-A Message '{}' passed filtering", value.getMessage()))
                .to("myTopic3");
        return stream;
    }

    @Bean
    public KStream<Key, Value> testStream2() {
        KStream<Key, Value> stream = secondaryStreamsBuilder.stream("myTopic3");
        stream
                .peek((key, value) -> LOGGER.info("KStream-B Streaming message '{}'", value.getMessage()))
                .filter((key, value) -> value.getMessage().contains("b"))
                .peek((key, value) -> LOGGER.info("KStream-B Message '{}' passed filtering", value.getMessage()))
                .to("myTopic4");
        return stream;
    }

}
