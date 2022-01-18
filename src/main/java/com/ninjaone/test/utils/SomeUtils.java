package com.ninjaone.test.utils;

import org.modelmapper.ModelMapper;

import com.ninjaone.test.dto.CustomerDeviceServiceDto;
import com.ninjaone.test.dto.CustomerDto;
import com.ninjaone.test.dto.DeviceDto;
import com.ninjaone.test.dto.ServiceDto;
import com.ninjaone.test.entities.Customer;
import com.ninjaone.test.entities.CustomerDeviceService;
import com.ninjaone.test.entities.Device;
import com.ninjaone.test.entities.Service;

public class SomeUtils {

	private static ModelMapper _mapper = new ModelMapper();

	public static DeviceDto convertToDto(Device device) {
		DeviceDto deviceDto = _mapper.map(device, DeviceDto.class);
		
	    return deviceDto;
	}
	
	public static Device convertToEntity(DeviceDto deviceDto) {
		Device device = _mapper.map(deviceDto, Device.class);
		
		return device;
	}
	
	public static ServiceDto convertToDto(Service service) {
		ServiceDto serviceDto = _mapper.map(service, ServiceDto.class);
		
	    return serviceDto;
	}
	
	public static Service convertToEntity(ServiceDto serviceDto) {
		Service device = _mapper.map(serviceDto, Service.class);
		
		return device;
	}
	
	public static CustomerDto convertToDto(Customer customer) {
		CustomerDto customerDto = _mapper.map(customer, CustomerDto.class);
		
	    return customerDto;
	}
	
	public static Customer convertToEntity(CustomerDto customerDto) {
		Customer device = _mapper.map(customerDto, Customer.class);
		
		return device;
	}
	
	public static CustomerDeviceServiceDto convertToDto(CustomerDeviceService custDeviceServ) {
		CustomerDeviceServiceDto dto = _mapper.map(custDeviceServ, CustomerDeviceServiceDto.class);
		
	    return dto;
	}
	
	public static CustomerDeviceService convertToEntity(CustomerDeviceServiceDto dto) {
		CustomerDeviceService entity = _mapper.map(dto, CustomerDeviceService.class);
		
		return entity;
	}

}
