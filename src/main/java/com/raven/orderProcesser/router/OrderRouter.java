package com.raven.orderProcesser.router;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raven.order.model.Order;
import com.raven.order.model.OrderUpdateEvent;
import com.raven.order.model.Status;
import com.raven.orderProcesser.publisher.OrderPublisher;
import com.raven.orderProcesser.repo.OrderRepository;

@Component
public class OrderRouter implements Router {

	@Autowired
	OrderPublisher orderPublisher;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public void post(Order order) {

		order.setOrderUpdateEvent(
				OrderUpdateEvent.builder().status(Status.PENDING).date(Date.from(Instant.now())).build());

		order.setResponseFrom("orderCapture");

		if (order.getStatus().equals(Status.FAILED)) {
			order.getOrderDetail().setStatus(Status.FAILED);
		} else {
			order.getOrderDetail().setStatus(Status.COMPLETED);
			order.setStatus(Status.COMPLETED);
		}
		orderRepo.save(order);

		orderPublisher.sendMessage(order.getOrderDetail());

		order.setOrderUpdateEvent(
				OrderUpdateEvent.builder().status(Status.COMPLETED).date(Date.from(Instant.now())).build());

		orderRepo.save(order);
	}

}
