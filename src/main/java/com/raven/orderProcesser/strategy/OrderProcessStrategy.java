package com.raven.orderProcesser.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderProcessStrategy {

	@Autowired
	ConsumerOrderStrategy consumerOrderStrategy;

	public OrderStrategy getStrategy(Order order) {

		return consumerOrderStrategy;
	}

}
