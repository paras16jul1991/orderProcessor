package com.raven.orderProcesser.router;

import com.raven.order.model.Order;

public interface Router {

	void post(Order order);
}
