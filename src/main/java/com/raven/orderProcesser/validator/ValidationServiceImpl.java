package com.raven.orderProcesser.validator;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.order.model.ValidationResponse;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	ValidationRepo validationRepo;

	@Override
	public CompletableFuture<ValidationResponse> addressValidCheck(Order order, String serviceName) {
		return CompletableFuture.completedFuture(validationRepo.addressValidCheck(order, serviceName));
	}

	@Override
	public CompletableFuture<ValidationResponse> inventoryCheck(Order order, String serviceName) {
		return CompletableFuture.completedFuture(validationRepo.inventoryCheck(order, serviceName));
	}

	@Override
	public CompletableFuture<ValidationResponse> paymentCompletionCheck(Order order, String serviceName) {
		return CompletableFuture.completedFuture(validationRepo.paymentCompletionCheck(order, serviceName));
	}

}
