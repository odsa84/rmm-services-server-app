package com.ninjaone.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServicesDto {

	private CustomerDto customer;
	
	private DeviceDto device;
	
	private ServiceDto service;

}
