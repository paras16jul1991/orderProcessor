package com.raven.orderProcesser.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.order.model.ServiceResponse;
import com.raven.orderProcesser.services.AdapterResponseHandler;

@Service
public class ConsumerOrderResponseStrategy {

	@Autowired
	AdapterResponseHandler adapterResponseHandler;

	@Value("#{'${consumer.order.sequence}'.split(',')}")
	List<String> serviceSequenceList;

	public Order processResponse(ServiceResponse serviceResponse, Order order) {

		return adapterResponseHandler.getResponseHandler(serviceResponse.getResponseFrom()).process(serviceResponse,
				order);

	}

}
