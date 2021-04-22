package com.raven.orderProcesser.validator;

import org.springframework.stereotype.Service;

import com.raven.order.model.Order;

@Service
public class ValidationRepoImpl implements ValidationRepo {

	@Override
	public AddressCheckResponse addressValidCheck(Order order, String serviceName) {
		return AddressCheckResponse.builder().serviceName(serviceName).build();
	}

	@Override
	public InventoryCheckResponse inventoryCheck(Order order, String serviceName) {
		return InventoryCheckResponse.builder().serviceName(serviceName).build();
	}

	@Override
	public PaymentCheckResponse paymentCompletionCheck(Order order, String serviceName) {
		return PaymentCheckResponse.builder().serviceName(serviceName).build();
	}

}
