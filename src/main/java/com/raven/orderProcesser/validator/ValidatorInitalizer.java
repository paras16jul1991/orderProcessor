package com.raven.orderProcesser.validator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
public class ValidatorInitalizer {

	@Autowired
	InventoryCheckValidator inventoryCheckValidator;

	@Autowired
	PaymentCompletionCheckValidator paymentCompletionCheckValidator;

	@Autowired
	AddressValidCheckValidator addressValidCheckValidator;

	@Getter
	List<Validator> validators = new ArrayList<Validator>();

	@PostConstruct
	void init() {
		validators.add(addressValidCheckValidator);
		validators.add(paymentCompletionCheckValidator);
		validators.add(inventoryCheckValidator);
	}
}
