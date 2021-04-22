package com.raven.orderProcesser.validator;

import java.util.concurrent.CompletableFuture;

import com.raven.order.model.Order;
import com.raven.order.model.ValidationResponse;

public interface ValidationService {

	public CompletableFuture<ValidationResponse> addressValidCheck(Order order, String serviceName);

	public CompletableFuture<ValidationResponse> inventoryCheck(Order order, String serviceName);

	public CompletableFuture<ValidationResponse> paymentCompletionCheck(Order order, String serviceName);

}
