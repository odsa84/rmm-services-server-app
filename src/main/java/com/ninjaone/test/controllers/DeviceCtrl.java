package com.ninjaone.test.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.test.dto.DeviceDto;
import com.ninjaone.test.entities.Device;
import com.ninjaone.test.services.DeviceSrv;
import com.ninjaone.test.utils.SomeUtils;

@RestController
@RequestMapping("/device")
public class DeviceCtrl {

	@Autowired
	private DeviceSrv deviceSrv;
	
	@GetMapping
	@ResponseBody
	public List<DeviceDto> getAllDevice() {
		List<DeviceDto> deviceDtoLst = new ArrayList<>();
		
		List<Device> result = deviceSrv.getAllDevice();
		
		result.forEach(re -> {
			deviceDtoLst.add(SomeUtils.convertToDto(re));
		});
		
		return deviceDtoLst;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
	public DeviceDto createDevice(@Valid @RequestBody DeviceDto deviceDto) {
		Device device = SomeUtils.convertToEntity(deviceDto);
		
		Device result = deviceSrv.createDevice(device);
		
		return SomeUtils.convertToDto(result);
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public DeviceDto updateDevice(@Valid @RequestBody DeviceDto deviceDto, @PathVariable("id") Long id) {
		
		if(!Objects.equals(id, deviceDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
		
		Device device = SomeUtils.convertToEntity(deviceDto);
		
		Device result = deviceSrv.updateDevice(device, id);
		
		return SomeUtils.convertToDto(result);
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteDevice(@PathVariable("id") Long id) {
		deviceSrv.deleteDevice(id);
	}
	
	

}
