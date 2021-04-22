package com.raven.orderProcesser.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.orderProcesser.handler.DefaultHandler;
import com.raven.orderProcesser.handler.DeliveryHandler;
import com.raven.orderProcesser.handler.Handler;
import com.raven.orderProcesser.handler.InventoryHandler;
import com.raven.orderProcesser.handler.OrderHandler;
import com.raven.orderProcesser.handler.ReverseInventoryHandler;
import com.raven.orderProcesser.handler.ShippingHandler;

@Service
public class AdapterHandlerInitializer {

	@Autowired
	InventoryHandler inventoryHandler;

	@Autowired
	ShippingHandler shippingHandler;

	@Autowired
	DeliveryHandler deliveryHandler;

	@Autowired
	OrderHandler orderHandler;

	@Autowired
	ReverseInventoryHandler reverseInventoryHandler;

	@Autowired
	DefaultHandler defaultHandler;
	
	@PostConstruct
	void init() {
		handlerList.add(inventoryHandler);
		handlerList.add(deliveryHandler);
		handlerList.add(shippingHandler);
		handlerList.add(orderHandler);
		handlerList.add(reverseInventoryHandler);
		handlerList.add(defaultHandler);
	}

	List<Handler> handlerList = new ArrayList<Handler>();

	List<Handler> getHandlerList() {

		return handlerList;
	}

}
