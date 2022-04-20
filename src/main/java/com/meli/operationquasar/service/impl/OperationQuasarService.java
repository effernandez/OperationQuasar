package com.meli.operationquasar.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.operationquasar.dto.PositionNaveDto;
import com.meli.operationquasar.dto.ResponseDTO;
import com.meli.operationquasar.dto.SatelliteDto;
import com.meli.operationquasar.dto.SatellitesDto;
import com.meli.operationquasar.entity.SatelliteRequestEntity;
import com.meli.operationquasar.repository.SatelliteRequestRepository;
import com.meli.operationquasar.service.IOperationQuasarService;

@Service
public class OperationQuasarService implements IOperationQuasarService {
	
	@Autowired
	LocationService locationService;
	@Autowired
	SecretMessageService secretMessageService;
	@Autowired
	SatelliteRequestRepository satelliteRequestRepository;
	
	
	@Override
	public ResponseDTO getTopSecret(SatellitesDto satellites) {
		
		ResponseDTO respuesta = new ResponseDTO();
		
		List<SatelliteDto> satellite = satellites.getSatellite();
		
		double[] result = locationService.getLocation(locationService.getCoordinates(),
				locationService.getDistance(satellite));
		
		PositionNaveDto positionNaveDto = new PositionNaveDto();
		positionNaveDto.setEjeX(result[0]);
		positionNaveDto.setEjeY(result[1]);
		
		respuesta.setPositionNave(positionNaveDto);
		respuesta.setMessageEmitido(secretMessageService.getMessage(satellites));
		
		
		return respuesta;
		
	}
	
	public void topScretSplitPost(String name, SatelliteDto satellite) {
		
		SatelliteRequestEntity satelliteRequestEntity = satelliteRequestRepository.findByName(name);
		
		if(satelliteRequestEntity != null) {
			satelliteRequestEntity.setDistance(satellite.getDistance());
			satelliteRequestEntity.setMessage(satellite.getMessage());
			satelliteRequestRepository.save(satelliteRequestEntity);
		}else {
			satelliteRequestEntity = new SatelliteRequestEntity();
			
			satelliteRequestEntity.setName(name);
			satelliteRequestEntity.setDistance(satellite.getDistance());
			satelliteRequestEntity.setMessage(satellite.getMessage());
			satelliteRequestRepository.save(satelliteRequestEntity);
		}
		
	}

}
