package com.meli.operationquasar.service;

import com.meli.operationquasar.dto.ResponseDTO;
import com.meli.operationquasar.dto.SatelliteDto;
import com.meli.operationquasar.dto.SatellitesDto;

public interface IOperationQuasarService {
	
	public ResponseDTO getTopSecret(SatellitesDto satelites);
	public void topScretSplitPost(String name, SatelliteDto satellite);
	public ResponseDTO topScretSplitGet() throws Throwable;

}
