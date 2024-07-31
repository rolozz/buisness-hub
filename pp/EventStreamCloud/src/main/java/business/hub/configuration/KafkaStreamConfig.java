package business.hub.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

/**
 * author Igor Ostrovsky
 * Настройка потока сообщений Kafka. Создание пользователя.
 * Настройка потока с помощью KStream и ProfileEventCreatingServices
 */

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @PostConstruct
    public void logProperties() {
        log.info("Bootstrap servers: {}", bootstrapServers);
    }

    private final Logger log = LoggerFactory.getLogger(KafkaStreamConfig.class);


    /**
     * Настройка чтения потока Kafka из раздела "dto-topic",
     * среда Serde для сериализации и десериализации потока байтов в string и json,
     * фильтрация потока путем поиска сообщений json с данными, определенными в фильтре.
     * @param builder
     * Получение построителя потока входящих параметров.
     * @return
     * Возврат десериализованного сообщения в формате json
     */
    @Bean
    public KStream<String, JsonNode> kStream(StreamsBuilder builder) {

        Serdes.StringSerde stringSerde = new Serdes.StringSerde();
        JsonSerde<JsonNode> jsonNodeSerde = new JsonSerde<>(JsonNode.class);

        log.info("ПОДКЛЮЧАЕМСЯ К KAFKA И DTO-TOPIC");

            KStream<String, JsonNode> stream = builder.stream("dto-topic", Consumed.with(stringSerde, jsonNodeSerde));

        log.info("ПОДКЛЮЧИЛИСЬ К KAFKA И DTO-TOPIC");

        stream.peek((key, value) -> log.info("СООБЩЕНИЕ ПОЛУЧЕНО СООБЩЕНИЕ ПОЛУЧЕНО - key: {}, value: {}", key, value))
                .filter((key, jsonNode) ->
                jsonNode.has("accountId") && jsonNode.has("firstName") && jsonNode.has("lastName"))
                .foreach((key, value) -> System.out.println("Filtered message: " + value));

        return stream;
    }

    @Value("${spring.kafka.consumer.properties.spring.json.trusted.packages}")
    private String trustedPackages;

    @PostConstruct
    public void checkProperties() {
        System.out.println("Trusted packages: " + trustedPackages);
    }

}
