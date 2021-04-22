package com.raven.orderProcesser.validator;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.raven.order.model.Order;
import com.raven.order.model.ValidationResponse;
import com.raven.order.model.ValidationResult;

@Component
public class AddressValidCheckValidator implements Validator {

	@Autowired
	ValidationService validationService;

	@Value("${validator.address-valid-check}")
	private String serviceName;

	@Override
	public CompletableFuture<ValidationResponse> fetchValidationDetails(Order order) {
		return validationService.addressValidCheck(order, serviceName);
	}

	@Override
	public ValidationResult validateResponse(Order order, ValidationResponse validationResponse) {

		ValidationResult result = ValidationResult.builder().build();

		result.setServiceName(getServiceName());
		result.setSuccessful(true);
		return result;
	}

	@Override
	public String getServiceName() {
		return serviceName;
	}

}
