package com.raven.orderProcesser.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.order.model.ValidationResponse;
import com.raven.order.model.ValidationResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidationExecutor {

	@Autowired
	ValidatorInitalizer validationInitalizer;

	public List<ValidationResult> executeValidations(Order order, List<String> validationList) {
		validationInitalizer.getValidators().forEach(validator -> log.info(validator.getServiceName()));

		List<CompletableFuture<ValidationResponse>> responseList = new ArrayList<CompletableFuture<ValidationResponse>>();

		validationInitalizer.getValidators().stream()
				.filter(validator -> validationList.contains(validator.getServiceName())).forEach(validator -> {

					log.info("Executing -> {} ", validator.getServiceName());
					responseList.add(validator.fetchValidationDetails(order));

				});

		List<ValidationResult> validationResultList = new ArrayList<ValidationResult>();

		List<ValidationResponse> validationResponseList = responseList.stream().map(CompletableFuture::join)
				.collect(Collectors.toList());

		validationResponseList.stream().forEach(response -> {

			Validator validatorI = validationInitalizer.getValidators().stream()
					.filter(validator -> validator.getServiceName().equals(response.getServiceName())).findFirst()
					.orElse(null);

			validationResultList.add(validatorI.validateResponse(order, response));
		});

		return validationResultList;
	}

}
