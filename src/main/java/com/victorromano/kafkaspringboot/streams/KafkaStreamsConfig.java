package com.victorromano.kafkaspringboot.streams;

import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaStreamsConfig {

    private final KafkaStreamsConfiguration kafkaStreamsConfiguration;

    @Autowired
    public KafkaStreamsConfig(KafkaStreamsConfiguration kafkaStreamsConfiguration) {
        this.kafkaStreamsConfiguration = kafkaStreamsConfiguration;
    }

    @Bean(name = "primaryKafkaStreamsBuilder")
    public StreamsBuilderFactoryBean primaryKafkaStreamsBuilder() {
        StreamsBuilderFactoryBean factoryBean = new StreamsBuilderFactoryBean(kafkaStreamsConfiguration);
        return factoryBean;
    }

    @Bean(name = "secondaryKafkaStreamsBuilder")
    public StreamsBuilderFactoryBean secondaryKafkaStreamsBuilder() {
        Properties properties = kafkaStreamsConfiguration.asProperties();
        Map<String, Object> mapProperties = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            mapProperties.put(key, properties.getProperty(key));
        }
        mapProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "test-streams-2");
        KafkaStreamsConfiguration kafkaStreamsConfiguration2 = new KafkaStreamsConfiguration(mapProperties);
        StreamsBuilderFactoryBean factoryBean = new StreamsBuilderFactoryBean(kafkaStreamsConfiguration2);
        return factoryBean;
    }

}
