package com.raven.orderProcesser.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raven.order.model.Order;
import com.raven.order.model.ValidationResult;
import com.raven.orderProcesser.services.AdapterRequestHandler;
import com.raven.orderProcesser.validator.ValidationExecutor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerOrderStrategy implements OrderStrategy {

	@Autowired
	private ValidationExecutor validationExecutor;

	@Autowired
	private AdapterRequestHandler adapterRequestHandler;

	@Value("#{'${consumer.order.sequence}'.split(',')}")
	List<String> serviceSequenceList;

	@Value("#{'${consumer.validation.sequence}'.split(',')}")
	List<String> validationSequenceList;

	@Override
	public void validateAndProcessOrder(Order order) {

		List<ValidationResult> validationResult = validationExecutor.executeValidations(order, validationSequenceList);

		order.setValidationResult(validationResult);

		if (isValidationSuccessful(validationResult)) {
			log.info("Validation Successful , processing Order ");
			processOrder(order);
		} else {
			log.info("Validation Failed ");
		}

	}

	private boolean isValidationSuccessful(List<ValidationResult> validationResult) {
		return !validationResult.stream().anyMatch(result -> !result.isSuccessful());
	}

	private void processOrder(Order order) {

		adapterRequestHandler.getHandler(order.getResponseFrom(), serviceSequenceList).process(order);
	}
}
