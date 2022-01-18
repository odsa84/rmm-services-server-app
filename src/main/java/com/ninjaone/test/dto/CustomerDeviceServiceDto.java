package com.ninjaone.test.dto;

import java.time.LocalDate;

import com.ninjaone.test.entities.Customer;
import com.ninjaone.test.entities.Device;
import com.ninjaone.test.entities.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDeviceServiceDto {

	private Customer customer;
	
	private Device device;
	
	private Service service;
	
	private float serviceCost;
	
	private LocalDate createdAt;

}
