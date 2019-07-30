# Kafka Spring Boot
Project with setup example to use Kafka, Avro and Schema Registry with Spring Boot.

# Running the Project

## Environment
Start Kafka with using Landoop's `fast-data-env` Docker container.
```
docker run --name kafka \
   --net=host \
   -e SAMPLEDATA=0 \
   -d landoop/fast-data-dev
```

## Build
```
$ cd kafka-spring-boot
$ ./mvnw clean package
$ java -jar target/kafka-spring-boot-*.jar
```