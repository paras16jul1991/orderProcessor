package com.raven.orderProcesser.validator;

import java.util.concurrent.CompletableFuture;

import com.raven.order.model.Order;
import com.raven.order.model.ValidationResponse;
import com.raven.order.model.ValidationResult;

public interface Validator {

	public CompletableFuture<ValidationResponse> fetchValidationDetails(Order order);

	public ValidationResult validateResponse(Order order, ValidationResponse validationResponse);

	public String getServiceName();
}
