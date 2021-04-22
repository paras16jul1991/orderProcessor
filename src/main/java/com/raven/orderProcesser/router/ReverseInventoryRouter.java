package com.raven.orderProcesser.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raven.order.model.Order;
import com.raven.order.model.ServiceResponse;
import com.raven.order.model.Status;
import com.raven.orderProcesser.repo.OrderRepository;
import com.raven.orderProcesser.strategy.OrderFailureProcessStrategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReverseInventoryRouter implements Router {

	@Autowired
	OrderFailureProcessStrategy orderFailureStrategy;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public void post(Order order) {

		log.info("Inventory Reversal completed.");

		order.setResponseFrom("reverse-inventory");

		orderRepo.save(order);

		// Pubish to Inventory Reversal service.

		ServiceResponse serviceResponse = ServiceResponse.builder().responseFrom("reverse-inventory")
				.status(Status.COMPLETED).build();
		orderFailureStrategy.getStrategy(order).process(serviceResponse, order);
	}

}
