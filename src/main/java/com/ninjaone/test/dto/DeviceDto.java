package com.ninjaone.test.dto;

import com.ninjaone.test.entities.TypeSysOperative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {

	private long id;
	
	private String system;
	
	private TypeSysOperative types;

}
