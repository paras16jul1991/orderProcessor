package com.raven.orderProcesser.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;

@Service
public class OrderFailureProcessStrategy {

	@Autowired
	ConsumerOrderFailureStrategy consumerOrderFailureStrategy;

	public ConsumerOrderFailureStrategy getStrategy(Order order) {

		return consumerOrderFailureStrategy;
	}

}
