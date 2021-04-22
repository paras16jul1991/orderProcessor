package com.raven.orderProcesser.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.order.model.ServiceResponse;
import com.raven.orderProcesser.services.AdapterRequestHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerOrderFailureStrategy {

	@Autowired
	AdapterRequestHandler adapterRequestHandler;

	@Value("#{'${consumer.order.reverse-sequence}'.split(',')}")
	List<String> serviceSequenceList;

	public void process(ServiceResponse serviceResponse, Order order) {

		// set to different handlers to start from begining
		log.info(" Failure handling for " + serviceResponse.getResponseFrom() + " service");
		adapterRequestHandler.getHandler(serviceResponse.getResponseFrom(), serviceSequenceList).process(order);

	}

}
