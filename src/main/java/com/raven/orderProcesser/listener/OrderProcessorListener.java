package com.raven.orderProcesser.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raven.order.model.Order;
import com.raven.order.model.ServiceResponse;
import com.raven.order.model.Status;
import com.raven.orderProcesser.repo.OrderRepository;
import com.raven.orderProcesser.strategy.OrderFailureProcessStrategy;
import com.raven.orderProcesser.strategy.OrderProcessStrategy;
import com.raven.orderProcesser.strategy.OrderResponseProcessStrategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderProcessorListener {

	private final Logger logger = LoggerFactory.getLogger(OrderProcessorListener.class);

	@Autowired
	OrderProcessStrategy orderProcessStrategy;

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	private OrderResponseProcessStrategy orderResponseProcessStrategy;

	@Autowired
	private OrderFailureProcessStrategy orderFailureProcessStrategy;

	@KafkaListener(topics = { "processor" }, groupId = "group_id")
	public void consume(String message) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", message));
		ServiceResponse serviceResponse = new ObjectMapper().readValue(message, ServiceResponse.class);

		log.info("Order id : " + serviceResponse.getOrderDetail().getId());
		Order order = orderRepo.findById("order:" + serviceResponse.getOrderDetail().getId()).get();

		order = orderResponseProcessStrategy.getStrategy(order).processResponse(serviceResponse, order);

		orderRepo.save(order);

		if (serviceResponse.getStatus().equals(Status.FAILED)) {
			serviceResponse.setResponseFrom("default");
			orderFailureProcessStrategy.getStrategy(order).process(serviceResponse, order);
		} else {
			order.setResponseFrom(serviceResponse.getResponseFrom());
			orderProcessStrategy.getStrategy(order).validateAndProcessOrder(order);
		}
	}
}
