package com.meli.operationquasar.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.operationquasar.dto.SatelliteDto;
import com.meli.operationquasar.dto.SatellitesDto;
import com.meli.operationquasar.service.IOperationQuasarService;

import io.swagger.annotations.ApiOperation;

@RestController
public class OperationQuasarController implements Serializable {

	private static final long serialVersionUID = -5628520609368526942L;

	private final Logger LOG = LoggerFactory.getLogger(OperationQuasarController.class);

	@Autowired
	IOperationQuasarService iOperationQuasarService;

	@ApiOperation(value = "obtiene la ubicación de la nave y el mensaje que emite", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/topsecret/", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> topsecret(@RequestBody SatellitesDto satellites) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(iOperationQuasarService.getTopSecret(satellites));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}

	}

	@ApiOperation(value = "obtiene la ubicación de la nave y el mensaje que emite", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/topsecret_split/{satellite_name}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> topScretSplitPost(@PathVariable("satellite_name") String satelliteName,
			@RequestBody SatelliteDto satellite) {
		try {
			iOperationQuasarService.topScretSplitPost(satelliteName, satellite);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
	}

	@ApiOperation(value = "obtiene la ubicación de la nave y el mensaje que emite", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/topsecret_split/")
	public  ResponseEntity<Object> topScretSplitConsul() {
		
		try {
//			return ResponseEntity.status(HttpStatus.OK).body(iOperationQuasarService.topScretSplitGet());
		} catch (Throwable e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("hola");
	}

}
