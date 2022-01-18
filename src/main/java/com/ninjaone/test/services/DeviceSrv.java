package com.ninjaone.test.services;

import java.util.List;

import com.ninjaone.test.entities.Device;

public interface DeviceSrv {
	
	List<Device> getAllDevice();
	
	Device createDevice(Device device);
	
	Device updateDevice(Device device, long id);
	
	void deleteDevice(long id);

}
