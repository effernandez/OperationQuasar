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
public class SatelliteDto {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("distance")
	private Double distance;
	
	@JsonProperty("message")
	public List<String> message;

}
