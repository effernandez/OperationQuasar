package com.meli.operationquasar.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
	
	@JsonProperty("position")
	private PositionNaveDto positionNave;
	
	@JsonProperty("message")
	private String messageEmitido;

}
