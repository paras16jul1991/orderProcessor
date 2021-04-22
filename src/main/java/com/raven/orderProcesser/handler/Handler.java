package com.raven.orderProcesser.handler;

import com.raven.order.model.Order;

public interface Handler {

	String getName();

	void process(Order order);
}
