package com.deliveryservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(Long orderId) {
        System.out.println("Order to be delivered : " + orderId);
    }


	/*
	 * @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
	 * containerFactory = "userKafkaListenerFactory") public void consumeJson(Order
	 * order) { System.out.println("Consumed JSON Message: " + order); }
	 */
}
