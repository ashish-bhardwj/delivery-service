package com.deliveryservice.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka
public class KafkaTest {
	
	@Autowired
	private EmbeddedKafkaBroker embeddedKafkaBroker;
	
	private static final String TOPIC="Kafka_Example";
	
	private Consumer<String, String> consumer;


    @Before
    public void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker));
        consumer = new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new StringDeserializer()).createConsumer();
        consumer.subscribe(singleton(TOPIC));
        consumer.poll(0);
    }


	private Collection<String> singleton(String topic2) {
		// TODO Auto-generated method stub
		return null;
	}


	@After
    public void tearDown() {
        consumer.close();
    }
    
    @Test
    public void kafkaSetup_withTopic_ensureSendMessageIsReceived() {
        // Arrange
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        Producer<String, String> producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new StringSerializer()).createProducer();

        // Act
        producer.send(new ProducerRecord<>(TOPIC, "id", "{\"event\":\"Test Event\"}"));
        producer.flush();

        // Assert
        ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, TOPIC);
        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.key()).isEqualTo("id");
        assertThat(singleRecord.value()).isEqualTo("{\"event\":\"Test Event\"}");
    }

}
