package com.raven.orderProcesser.publisher;

import org.springframework.kafka.core.KafkaTemplate;

import com.raven.kafka.Producer;
import com.raven.order.model.OrderDetail;

public class ShippingPublisher extends Producer<String, OrderDetail> {

	public ShippingPublisher(String topic, KafkaTemplate<String, String> template) {
		super(topic, template);
		// TODO Auto-generated constructor stub
	}

}
