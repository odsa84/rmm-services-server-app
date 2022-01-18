package com.ninjaone.test.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ninjaone.test.entities.Customer;
import com.ninjaone.test.entities.CustomerDeviceService;
import com.ninjaone.test.entities.CustomerDeviceServiceId;
import com.ninjaone.test.entities.Device;
import com.ninjaone.test.entities.Service;

@Repository
public interface CustomerDeviceServiceDao extends JpaRepository<CustomerDeviceService, CustomerDeviceServiceId> {
	
	List<CustomerDeviceService> findByCustomerAndDeviceAndService(Customer cust, Device device, Service service);
	
	List<CustomerDeviceService> findByCustomer(Customer cust);
	
	@Query("SELECT SUM(cds.serviceCost) AS cost FROM CustomerDeviceService cds WHERE cds.customer = :customer AND cds.createdAt BETWEEN :startDate AND :endDate")
	double sumCustomerServicesCost(Customer customer, LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT COUNT(DISTINCT cds.device) FROM CustomerDeviceService cds WHERE cds.customer = :customer AND cds.createdAt BETWEEN :startDate AND :endDate")
	int countDistinctDevicesByCustomer(Customer customer, LocalDate startDate, LocalDate endDate);

}
