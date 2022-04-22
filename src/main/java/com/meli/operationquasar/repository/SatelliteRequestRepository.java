package com.meli.operationquasar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.operationquasar.entity.SatelliteRequestEntity;

public interface SatelliteRequestRepository extends JpaRepository<SatelliteRequestEntity, Long>{
	
	public SatelliteRequestEntity findByName(String name );

}
