package com.raven.orderProcesser.router;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raven.order.model.InventoryEvent;
import com.raven.order.model.Order;
import com.raven.order.model.Status;
import com.raven.orderProcesser.publisher.InventoryPublisher;
import com.raven.orderProcesser.repo.OrderRepository;

@Component
public class InventoryRouter implements Router {

	@Autowired
	InventoryPublisher inventoryPublisher;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public void post(Order order) {

		order.setInventoryEvent(InventoryEvent.builder().status(Status.PENDING).date(Date.from(Instant.now())).build());

		orderRepo.save(order);

		inventoryPublisher.sendMessage(order.getOrderDetail());
	}

}
