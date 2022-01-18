package com.ninjaone.test.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer_device_service")
public class CustomerDeviceService {

	@EmbeddedId
	private CustomerDeviceServiceId customerDeviceServiceId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    @JoinColumn(name = "id_customer")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("deviceId")
	@JoinColumn(name = "id_device")
	private Device device;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("serviceId")
	@JoinColumn(name = "id_service")
	private Service service;
	
	@Column(name="service_cost")
	private float serviceCost;
	
	@Column(name="created_at")
	private LocalDate createdAt;

}
