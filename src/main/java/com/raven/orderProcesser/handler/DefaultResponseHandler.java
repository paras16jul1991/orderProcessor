package com.raven.orderProcesser.handler;

import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.order.model.ServiceResponse;

@Service
public class DefaultResponseHandler implements ResponseHandler {

	@Override
	public Order process(ServiceResponse serviceResponse, Order order) {
		return order;
	}

}
