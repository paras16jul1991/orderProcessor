package com.raven.orderProcesser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.raven.orderProcesser.publisher.DeliveryPublisher;
import com.raven.orderProcesser.publisher.InventoryPublisher;
import com.raven.orderProcesser.publisher.OrderPublisher;
import com.raven.orderProcesser.publisher.ShippingPublisher;

@Configuration
public class RavenConfig {

	@Value("${raven.kafka.topic.inventory-ms-request}")
	private String inventoryTopic;

	@Value("${raven.kafka.topic.delivery-ms-request}")
	private String deliveryTopic;

	@Value("${raven.kafka.topic.shipping-ms-request}")
	private String shippingTopic;

	@Value("${raven.kafka.topic.orderupdate-ms-request}")
	private String orderUpdateTopic;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Bean
	InventoryPublisher inventoryPublisher() {
		return new InventoryPublisher(inventoryTopic, kafkaTemplate);
	}

	@Bean
	DeliveryPublisher deliveryPublisher() {
		return new DeliveryPublisher(deliveryTopic, kafkaTemplate);
	}

	@Bean
	ShippingPublisher shippingPublisher() {
		return new ShippingPublisher(shippingTopic, kafkaTemplate);
	}

	@Bean
	OrderPublisher orderPublisher() {
		return new OrderPublisher(orderUpdateTopic, kafkaTemplate);
	}

}
