package com.raven.orderProcesser.validator;

import com.raven.order.model.Order;

public interface ValidationRepo {

	public AddressCheckResponse addressValidCheck(Order order, String serviceName);

	public InventoryCheckResponse inventoryCheck(Order order, String serviceName);

	public PaymentCheckResponse paymentCompletionCheck(Order order, String serviceName);

}