package com.ninjaone.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.test.entities.Service;

@Repository
public interface ServiceDao extends JpaRepository<Service, Long> {

}
