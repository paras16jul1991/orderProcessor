package com.raven.orderProcesser.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.raven.order.model.Order;
import com.raven.orderProcesser.router.ReverseInventoryRouter;

@Component
public class ReverseInventoryHandler implements Handler {

	@Value("${service.reverse-inventory-ms}")
	String name;

	@Autowired
	private ReverseInventoryRouter reverseInventoryRouter;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void process(Order order) {
		reverseInventoryRouter.post(order);
	}
}
