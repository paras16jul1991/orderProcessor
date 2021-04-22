package com.raven.orderProcesser.router;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raven.order.model.DeliveryEvent;
import com.raven.order.model.Order;
import com.raven.order.model.Status;
import com.raven.orderProcesser.publisher.DeliveryPublisher;
import com.raven.orderProcesser.repo.OrderRepository;

@Component
public class DeliveryRouter implements Router {

	@Autowired
	DeliveryPublisher deliveryPublisher;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public void post(Order order) {

		order.setDeliveryEvent(DeliveryEvent.builder().date(Date.from(Instant.now())).build());

		orderRepo.save(order);

		deliveryPublisher.sendMessage(order.getOrderDetail());
	}

}
