package com.ninjaone.test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.test.dao.DeviceDao;
import com.ninjaone.test.entities.Device;

@Service
public class DeviceSrvImpl implements DeviceSrv {
	
	@Autowired
	private DeviceDao deviceDao;

	@Override
	public List<Device> getAllDevice() {
		
		return (List<Device>) deviceDao.findAll();
	}

	@Override
	public Device createDevice(Device device) {
		
		return deviceDao.save(device);
	}

	@Override
	public Device updateDevice(Device device, long id) {
		
		return deviceDao.findById(id)
			      .map(dvc -> {
			    	  dvc.setSystem(device.getSystem());
			        dvc.setTypes(device.getTypes());
			        return deviceDao.save(dvc);
			      })
			      .orElseGet(() -> {
			        device.setId(id);
			        return deviceDao.save(device);
			      });
	}

	@Override
	public void deleteDevice(long id) {
		
		deviceDao.deleteById(id);
	}

}
