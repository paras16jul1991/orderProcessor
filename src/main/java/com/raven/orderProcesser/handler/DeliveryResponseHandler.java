package com.raven.orderProcesser.handler;

import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.order.model.ServiceResponse;
import com.raven.order.model.Status;

@Service
public class DeliveryResponseHandler implements ResponseHandler {

	@Override
	public Order process(ServiceResponse serviceResponse, Order order) {

		order.getDeliveryEvent().setStatus(serviceResponse.getStatus());
		order.getDeliveryEvent().setReason(serviceResponse.getReason());
		if (serviceResponse.getStatus().equals(Status.FAILED)) {
			order.setStatus(Status.FAILED);
		}
		return order;
	
	}

}
