package com.raven.orderProcesser.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.orderProcesser.router.OrderRouter;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class OrderHandler implements Handler {

	@Value("${service.orderupdate-ms}")
	String name;

	@Autowired
	private OrderRouter orderRouter;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void process(Order order) {
		orderRouter.post(order);
	}

}
