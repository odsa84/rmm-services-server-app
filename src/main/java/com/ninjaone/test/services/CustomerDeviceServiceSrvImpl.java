package com.ninjaone.test.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.test.dao.CustomerDao;
import com.ninjaone.test.dao.CustomerDeviceServiceDao;
import com.ninjaone.test.entities.Customer;
import com.ninjaone.test.entities.CustomerDeviceService;
import com.ninjaone.test.entities.CustomerDeviceServiceId;
import com.ninjaone.test.entities.Device;
import com.ninjaone.test.exceptions.CustomExceptionHandler;

@Service
public class CustomerDeviceServiceSrvImpl implements CustomerDeviceServiceSrv {
	
	@Autowired
	private CustomerDeviceServiceDao custDeviceSrviceDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	private final static int ANTIVIRUS = 1;
	private final static int CLOUDBERRY = 2;
	private final static int PSA = 3;
	
	@Override
	public List<CustomerDeviceService> getServicesByCustomer(long idCust) {
		
		Customer customer = customerDao.findById(idCust).get();
		
		return custDeviceSrviceDao.findByCustomer(customer);
	}

	@Override
	public CustomerDeviceService createCustomerService(Customer customer, com.ninjaone.test.entities.Service service, Device device) {
		
		List<CustomerDeviceService> cds = custDeviceSrviceDao.findByCustomerAndDeviceAndService(customer, device, service);
		
		if(cds.isEmpty()) {
			float cost = 0;
			if(service.getId() == ANTIVIRUS) {
				cost = device.getTypes().getName().equals("Windows Workstation") || device.getTypes().getName().equals("Windows Server") ? 5 : 7;
			} else if(service.getId() == CLOUDBERRY) {
				cost = 3;
			} else if(service.getId() == PSA) {
				cost = 2;
			} else {
				cost = 1;
			}
			
			CustomerDeviceServiceId cdsId = new CustomerDeviceServiceId(customer.getId(), service.getId(), device.getId());
			CustomerDeviceService custDeviceService = new CustomerDeviceService(cdsId, customer, device, service, cost, LocalDate.now());
			
			return custDeviceSrviceDao.save(custDeviceService);
		}
		
		throw new CustomExceptionHandler("You can't add the same service to your account");
	}

	@Override
	public void deleteCustomerService(long idCustomer, long idDevice, long idService) {

		CustomerDeviceServiceId cdsId = new CustomerDeviceServiceId(idCustomer, idDevice, idService);
		
		custDeviceSrviceDao.deleteById(cdsId);
	}
	
	@Override
	@Transactional
	public float calculateTotalMonthlyCost(long idCustomer, LocalDate startDate, LocalDate endDate) {
		Customer cust = customerDao.findById(idCustomer).get();
		
		Integer contDevices = custDeviceSrviceDao.countDistinctDevicesByCustomer(cust, startDate, endDate);
		double monthlyCost = custDeviceSrviceDao.sumCustomerServicesCost(cust, startDate, endDate);
		
		float result = (contDevices.floatValue() * 4) + (float)monthlyCost;
		
		return result;
	}
}
