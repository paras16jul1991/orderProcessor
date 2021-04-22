package com.raven.orderProcesser.handler;

import com.raven.order.model.Order;
import com.raven.order.model.ServiceResponse;

public interface ResponseHandler {

	Order process(ServiceResponse serviceResponse, Order order);

}
