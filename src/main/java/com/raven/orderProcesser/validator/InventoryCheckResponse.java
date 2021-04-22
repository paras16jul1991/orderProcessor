package com.raven.orderProcesser.validator;

import com.raven.order.model.ValidationResponse;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryCheckResponse implements ValidationResponse {

	private String serviceName;

}
