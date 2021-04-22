package com.raven.orderProcesser.publisher;

import org.springframework.kafka.core.KafkaTemplate;

import com.raven.kafka.Producer;
import com.raven.order.model.OrderDetail;

public class InventoryPublisher extends Producer<String, OrderDetail> {

	public InventoryPublisher(String topic, KafkaTemplate<String, String> template) {
		super(topic, template);
	}

}
