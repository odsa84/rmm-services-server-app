package com.ninjaone.test.services;

import java.time.LocalDate;
import java.util.List;

import com.ninjaone.test.entities.Customer;
import com.ninjaone.test.entities.CustomerDeviceService;
import com.ninjaone.test.entities.Device;
import com.ninjaone.test.entities.Service;

public interface CustomerDeviceServiceSrv {
	
	List<CustomerDeviceService> getServicesByCustomer(long idCustomer);
	
	CustomerDeviceService createCustomerService(Customer customer, Service service, Device device);
	
	void deleteCustomerService(long idCustomer, long idDevice, long idService);
	
	float calculateTotalMonthlyCost(long idCustomer, LocalDate startDate, LocalDate endDate);

}
