package com.meli.operationquasar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.meli.operationquasar.entity.SatelliteRequestEntity;

public interface SatelliteRequestRepository extends JpaRepository<SatelliteRequestEntity, Long>{
	
	public SatelliteRequestEntity findByName(@Param("name") String name );

}
