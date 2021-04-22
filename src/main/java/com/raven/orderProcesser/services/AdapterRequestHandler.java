package com.raven.orderProcesser.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raven.orderProcesser.handler.DefaultHandler;
import com.raven.orderProcesser.handler.Handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdapterRequestHandler {

	@Autowired
	AdapterHandlerInitializer adapterHandlerInitializer;

	@Autowired
	DefaultHandler defaultHandler;

	public Handler getHandler(String responseFrom, List<String> sequenceList) {

		log.info(responseFrom + " in Adapter Request Handler");
		sequenceList.forEach(x -> log.info("Adapter - " + x));
		String nextHandler = getNextHandler(responseFrom, sequenceList);
		log.info("Next Handler -> " + nextHandler);

		return adapterHandlerInitializer.getHandlerList().stream()
				.filter(handler -> nextHandler.equals(handler.getName())).findFirst().orElse(defaultHandler);
	}

	private String getNextHandler(String responseFrom, List<String> sequenceList) {

		String nextAdapter = null;

		for (int i = 0; i < sequenceList.size(); i++) {
			if (responseFrom.equals(sequenceList.get(i)) && i + 1 <= sequenceList.size()) {
				nextAdapter = sequenceList.get(i + 1);
				log.info("Next Adapter call is " + nextAdapter);
				break;
			}
		}

		return nextAdapter;
	}

}
