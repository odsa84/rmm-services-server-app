package com.ninjaone.test.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.test.entities.Device;

@Repository
public interface DeviceDao extends CrudRepository<Device, Long> {

}
