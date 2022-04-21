package com.meli.operationquasar.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.plaf.basic.BasicTreeUI.TreeHomeAction;

import org.aspectj.util.LangUtil.ProcessController.Thrown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.meli.operationquasar.dto.PositionNaveDto;
import com.meli.operationquasar.dto.ResponseDTO;
import com.meli.operationquasar.dto.SatelliteDto;
import com.meli.operationquasar.dto.SatellitesDto;
import com.meli.operationquasar.entity.SatelliteRequestEntity;
import com.meli.operationquasar.repository.SatelliteRequestRepository;
import com.meli.operationquasar.service.IOperationQuasarService;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class OperationQuasarService implements IOperationQuasarService {
	
	@Autowired
	LocationService locationService;
	@Autowired
	SecretMessageService secretMessageService;
	@Autowired
	SatelliteRequestRepository satelliteRequestRepository;
	@Autowired
	Environment properties;
	
	
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
	
	public ResponseDTO topScretSplitGet() throws Throwable {
		ResponseDTO result = new ResponseDTO();
		
		int numberSatellites = Integer.parseInt(properties.getProperty("satellites"));
		
		List<SatelliteRequestEntity> listSatelliteRt =  satelliteRequestRepository.findAll();
		
		if(listSatelliteRt.size() != numberSatellites) {
			throw new Exception(properties.getProperty("menssages"));
		
		}else {
			
			List<SatelliteDto> listSatellites = new ArrayList<>();
			SatellitesDto satellitesDto = new SatellitesDto();
			
			for (SatelliteRequestEntity satelliteRequestEntity:listSatelliteRt) {
				
				SatelliteDto satelliteDto = new SatelliteDto();
				
				satelliteDto.setName(satelliteRequestEntity.getName());
				satelliteDto.setDistance(satelliteRequestEntity.getDistance());
				satelliteDto.setMessage(satelliteRequestEntity.getMessage());
				
				listSatellites.add(satelliteDto);
			}
			
			satellitesDto.setSatellite(listSatellites);
			result = getTopSecret(satellitesDto);
			
		}
		

		
		return  result;
	}

}
