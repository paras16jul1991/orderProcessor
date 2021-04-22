package com.raven.orderProcesser.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.orderProcesser.router.ShippingRouter;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ShippingHandler implements Handler {

	@Value("${service.shipping-ms}")
	String name;

	@Autowired
	private ShippingRouter shippingRouter;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void process(Order order) {
		shippingRouter.post(order);
	}

}
