package com.raven.orderProcesser.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.orderProcesser.handler.DefaultResponseHandler;
import com.raven.orderProcesser.handler.DeliveryResponseHandler;
import com.raven.orderProcesser.handler.InventoryResponseHandler;
import com.raven.orderProcesser.handler.OrderResponseHandler;
import com.raven.orderProcesser.handler.ResponseHandler;
import com.raven.orderProcesser.handler.ShippingResponseHandler;

@Service
public class ResponseAdapterHandlerInitializer {

	@Autowired
	InventoryResponseHandler inventoryResponseHandler;

	@Autowired
	ShippingResponseHandler shippingResponseHandler;

	@Autowired
	DeliveryResponseHandler deliveryResponseHandler;

	@Autowired
	OrderResponseHandler orderResponseHandler;

	@Autowired
	DefaultResponseHandler defaultResponseHandler;

	Map<String, ResponseHandler> handlerMap = new HashMap<String, ResponseHandler>();

	@PostConstruct
	public void intilize() {

		handlerMap.put("inventory", inventoryResponseHandler);
		handlerMap.put("shipping", shippingResponseHandler);
		handlerMap.put("delivery", deliveryResponseHandler);
		handlerMap.put("order", orderResponseHandler);
		handlerMap.put("default", defaultResponseHandler);

	}

	public ResponseHandler getHandler(String adapterName) {

		return handlerMap.get(adapterName);

	}
}
