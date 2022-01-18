package com.ninjaone.test.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.test.dto.CustomerDeviceServiceDto;
import com.ninjaone.test.dto.CustomerServicesDto;
import com.ninjaone.test.dto.MonthlyCost;
import com.ninjaone.test.entities.Customer;
import com.ninjaone.test.entities.CustomerDeviceService;
import com.ninjaone.test.entities.Device;
import com.ninjaone.test.entities.Service;
import com.ninjaone.test.services.CustomerDeviceServiceSrv;
import com.ninjaone.test.utils.SomeUtils;

@RestController
@RequestMapping("/customerService")
public class CustomerDeviceServiceCtrl {
	
	@Autowired
	private CustomerDeviceServiceSrv custDeviceServiceSrv;
	
	@GetMapping("/serviceByCustomer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDeviceServiceDto> getServicesByCustomer(@PathVariable("id") Long id) {
		
		List<CustomerDeviceServiceDto> dtoList = new ArrayList<>();
		
		List<CustomerDeviceService> result = custDeviceServiceSrv.getServicesByCustomer(id);
		
		result.forEach(re -> {
			dtoList.add(SomeUtils.convertToDto(re));
		});
		
		return dtoList;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDeviceServiceDto> createCustomerService(@Valid  @RequestBody CustomerServicesDto custServicesDto) {
		
		Device device = SomeUtils.convertToEntity(custServicesDto.getDevice());
		Customer customer = SomeUtils.convertToEntity(custServicesDto.getCustomer());
		Service service = SomeUtils.convertToEntity(custServicesDto.getService());
		
		CustomerDeviceService result = custDeviceServiceSrv.createCustomerService(customer, service, device);
		
		return ResponseEntity.ok(SomeUtils.convertToDto(result));
	}

	@DeleteMapping(value="/{idCust}/{idDev}/{idServ}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomerService(@PathVariable("idCust") Long idCust, @PathVariable("idDev") Long idDev, @PathVariable("idServ") Long idServ) {
		
		custDeviceServiceSrv.deleteCustomerService(idCust, idDev, idServ);
	}
	
	@GetMapping(value="/calculateTotalMonthy/{id}/{startDate}/{endDate}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<MonthlyCost> calculateTotalMonthlyCost(@PathVariable("id") long id, @PathVariable("startDate") String startDate, 
			@PathVariable("endDate") String endDate) {
		
		MonthlyCost result = new MonthlyCost();
		result.setMonthlyCost(custDeviceServiceSrv.calculateTotalMonthlyCost(id, LocalDate.parse(startDate), LocalDate.parse(endDate)));
		
		return ResponseEntity.ok(result);
	}

}
