package com.raven.orderProcesser.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.orderProcesser.router.DeliveryRouter;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class DeliveryHandler implements Handler {

	@Value("${service.delivery-ms}")
	String name;

	@Autowired
	private DeliveryRouter deliveryRouter;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void process(Order order) {
		deliveryRouter.post(order);
	}

}
