# Kafka Spring Boot
Project with setup example to use Kafka, Avro and Schema Registry with Spring Boot.

# Testing the Project

## Environment Setup
Start Kafka with using Landoop's [`fast-data-env`](https://hub.docker.com/r/landoop/fast-data-dev) Docker container.
```
docker run --name kafka \
   --net=host \
   -e SAMPLEDATA=0 \
   -d landoop/fast-data-dev
```

## Build
```
cd kafka-spring-boot
./mvnw clean package
```

## Run
```
java -jar target/kafka-spring-boot-*.jar
```

## Test
To generate random messages to topic:
```
curl -X POST http://localhost:8080/sample
```
To your own message to topic:
```
curl -X POST \
  http://localhost:8080/message \
  -H 'Content-Type: application/json' \
  -d '{
        "message": "my own message"
      }'
```