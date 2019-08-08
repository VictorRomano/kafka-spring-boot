package com.victorromano.kafkaspringboot.streams;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    private final KafkaProperties kafkaProperties;

    @Autowired
    public KafkaStreamsConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        // Common properties between consumers, producers and streams
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(StreamsConfig.CLIENT_ID_CONFIG, kafkaProperties.getClientId());
        props.putAll(kafkaProperties.getProperties()); // Adds Schema Registry property
        // Specific properties to streams
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getStreams().getApplicationId());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);

        return new KafkaStreamsConfiguration(props);
    }

}
