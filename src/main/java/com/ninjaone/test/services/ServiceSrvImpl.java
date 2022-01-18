package com.ninjaone.test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ninjaone.test.dao.ServiceDao;
import com.ninjaone.test.entities.Service;

@org.springframework.stereotype.Service
public class ServiceSrvImpl implements ServiceSrv {
	
	@Autowired
	private ServiceDao serviceDao;

	@Override
	public List<Service> getAllService() {
		
		return serviceDao.findAll();
	}


}
