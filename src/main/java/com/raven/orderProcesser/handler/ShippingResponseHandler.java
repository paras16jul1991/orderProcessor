package com.raven.orderProcesser.handler;

import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.order.model.ServiceResponse;
import com.raven.order.model.Status;

@Service
public class ShippingResponseHandler implements ResponseHandler {

	@Override
	public Order process(ServiceResponse serviceResponse, Order order) {
		order.getShippingEvent().setStatus(serviceResponse.getStatus());
		order.getShippingEvent().setReason(serviceResponse.getReason());
		if (serviceResponse.getStatus().equals(Status.FAILED)) {
			order.setStatus(Status.FAILED);
		}

		return order;
	}

}
