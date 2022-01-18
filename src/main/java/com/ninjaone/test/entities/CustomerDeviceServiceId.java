package com.ninjaone.test.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomerDeviceServiceId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="id_customer")
	long customerId;
	
	@Column(name="id_device")
	long deviceId;
	
	@Column(name="id_service")
	long serviceId;

}
