package com.raven.orderProcesser.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.orderProcesser.router.InventoryRouter;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class InventoryHandler implements Handler {

	@Value("${service.inventory-ms}")
	String name;

	@Autowired
	private InventoryRouter inventoryRouter;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void process(Order order) {
		inventoryRouter.post(order);
	}

}
