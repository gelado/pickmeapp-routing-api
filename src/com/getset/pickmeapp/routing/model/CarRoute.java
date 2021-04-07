package com.getset.pickmeapp.routing.model;

import java.util.ArrayList;
import java.util.List;

import jsprit.core.problem.solution.route.activity.TourActivities;
import jsprit.core.problem.solution.route.activity.TourActivity;

public class CarRoute {
	
	private String carId;
	private List<Place> route;
	
	public CarRoute() {
		super();
	}
	
	public CarRoute(List<TourActivity> activities, TourActivities activities2) {
		
		this.route = new ArrayList<Place>(activities.size());
		
		for(TourActivity activity : activities) {
			
			Place place = new Place();
			place.setPlaceId(activity.getLocation().getId());
			place.setY(activity.getLocation().getCoordinate().getY());
			place.setX(activity.getLocation().getCoordinate().getX());
			
			if(!route.contains(place)) {
				route.add(place);
			}
			
		}
		
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public List<Place> getRoute() {
		return route;
	}

	public void setRoute(List<Place> route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "CarRoute [carId=" + carId + ", route=" + route + "]";
	}
	
}
