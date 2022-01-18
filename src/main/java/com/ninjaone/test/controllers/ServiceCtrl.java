package com.ninjaone.test.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.test.dto.ServiceDto;

import com.ninjaone.test.entities.Service;
import com.ninjaone.test.services.ServiceSrv;
import com.ninjaone.test.utils.SomeUtils;

@RestController
@RequestMapping("/service")
public class ServiceCtrl {

	@Autowired
	private ServiceSrv serviceSrv;
	
	@GetMapping
	@ResponseBody
	public List<ServiceDto> getAllService() {
		
		List<ServiceDto> serviceDtoLst = new ArrayList<>();
		
		List<Service> result = serviceSrv.getAllService();
		
		result.forEach(re -> {
			serviceDtoLst.add(SomeUtils.convertToDto(re));
		});
		
		return serviceDtoLst;
	}
}
