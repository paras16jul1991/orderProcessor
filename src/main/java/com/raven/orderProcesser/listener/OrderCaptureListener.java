package com.raven.orderProcesser.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raven.order.model.Order;
import com.raven.order.model.OrderDetail;
import com.raven.order.model.Status;
import com.raven.orderProcesser.repo.OrderRepository;
import com.raven.orderProcesser.strategy.OrderProcessStrategy;

@Service
public class OrderCaptureListener {

	private final Logger logger = LoggerFactory.getLogger(OrderCaptureListener.class);

	@Autowired
	OrderProcessStrategy orderProcessStrategy;

	@Autowired
	OrderRepository orderRepo;

	@KafkaListener(topics = { "orderCapture" }, groupId = "group_id")
	public void consume(String message) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", message));
		OrderDetail orderDetail = new ObjectMapper().readValue(message, OrderDetail.class);

		Order order = buildOrder(orderDetail);

		orderRepo.save(order);

		orderProcessStrategy.getStrategy(order).validateAndProcessOrder(order);
	}

	private Order buildOrder(OrderDetail orderDetail) {
		return Order.builder().id("order:" + orderDetail.getId()).orderDetail(orderDetail).responseFrom("ordercapture")
				.status(Status.PENDING).build();
	}
}
