package com.getset.pickmeapp.routing.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jsprit.core.problem.solution.route.VehicleRoute;

public class ProblemSolution {
	
	private List<CarRoute> solutionRoutes;

	public ProblemSolution() {
		super();
	}
	
	public ProblemSolution(Collection<VehicleRoute> routes) {
		
		solutionRoutes = new ArrayList<>();
		
		for(VehicleRoute route : routes) {
			
			CarRoute currentCar = new CarRoute(route.getActivities(), route.getTourActivities());
			currentCar.setCarId(route.getVehicle().getId());
			solutionRoutes.add(currentCar);
			
		}
		
	}

	public List<CarRoute> getSolutionRoutes() {
		return solutionRoutes;
	}

	public void setSolutionRoutes(List<CarRoute> solutionRoutes) {
		this.solutionRoutes = solutionRoutes;
	}

}
