package com.raven.orderProcesser.strategy;

import com.raven.order.model.Order;

public interface OrderStrategy {

	public void validateAndProcessOrder(Order order);
}
