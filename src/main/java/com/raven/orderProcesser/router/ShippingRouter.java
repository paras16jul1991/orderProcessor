package com.raven.orderProcesser.router;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raven.order.model.Order;
import com.raven.order.model.ShippingEvent;
import com.raven.order.model.Status;
import com.raven.orderProcesser.publisher.ShippingPublisher;
import com.raven.orderProcesser.repo.OrderRepository;

@Component
public class ShippingRouter implements Router {

	@Autowired
	ShippingPublisher ShippingPublisher;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public void post(Order order) {

		order.setShippingEvent(ShippingEvent.builder().status(Status.PENDING).date(Date.from(Instant.now())).build());
		orderRepo.save(order);

		ShippingPublisher.sendMessage(order.getOrderDetail());
	}

}
