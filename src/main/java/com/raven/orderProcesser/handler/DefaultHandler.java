package com.raven.orderProcesser.handler;

import org.springframework.stereotype.Component;

import com.raven.order.model.Order;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultHandler implements Handler {

	@Override
	public String getName() {
		return "defaultHandler";
	}

	@Override
	public void process(Order order) {
		log.info("Default Handler called");
	}

}
