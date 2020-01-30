package com.victorromano.kafkaspringboot.streams;

import com.victorromano.avro.kafkaspringboot.Value;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

@Component
@EnableKafkaStreams
public class SampleKafkaStreams {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleKafkaStreams.class);

    @Bean
    public KStream<String, Value> testStream(StreamsBuilder kStreamBuilder) {
        KStream<String, Value> streamTopic2 = kStreamBuilder.stream("topic2");
        KStream<String, Value> streamTopic3 = kStreamBuilder.stream("topic3");

        streamTopic2
                .join(streamTopic3,
                        (key, value) -> Value.newBuilder().setMessage(value.getMessage()).build(),
                        JoinWindows.of(300000))
                .to("topic4");

        return streamTopic2;
    }

}
