package com.meli.operationquasar.service.impl;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.operationquasar.dto.SatelliteDto;

@Service
public class LocationService {
	
	@Autowired
	Environment properties;
	
	public double[] getLocation(double[][] positions, double[] distances) {

		TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
		NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction,
				new LevenbergMarquardtOptimizer());

		return nSolver.solve().getPoint().toArray();
	}

	public double[] getDistance(List<SatelliteDto> satellite) {
		double[] distance = new double[satellite.size()];
		for (int i = 0; i < satellite.size(); i++) {
			distance[i] = satellite.get(i).getDistance();
		}
		return distance;

	}

	public double[][] getCoordinates() {
		int numberSatellites = Integer.parseInt(properties.getProperty("satellites"));
		String[] satellite;
		double[][] positions = new double[numberSatellites][];

		for (int i = 0; i < numberSatellites; i++) {
			satellite = properties.getProperty("satellite." + i +"." + "position").split(",");
			positions[i] = Arrays.stream(satellite).map(Double::valueOf).mapToDouble(Double::doubleValue).toArray();
		}
		return positions;

	}

}
