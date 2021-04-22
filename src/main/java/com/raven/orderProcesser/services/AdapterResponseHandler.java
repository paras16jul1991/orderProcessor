package com.raven.orderProcesser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.orderProcesser.handler.DefaultResponseHandler;
import com.raven.orderProcesser.handler.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdapterResponseHandler {

	@Autowired
	ResponseAdapterHandlerInitializer responseAdapterHandlerInitializer;

	@Autowired
	DefaultResponseHandler defaultResponseHandler;

	public ResponseHandler getResponseHandler(String responseFrom) {

		return responseAdapterHandlerInitializer.getHandler(responseFrom);
	}

}
