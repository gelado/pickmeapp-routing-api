package com.getset.pickmeapp.routing.model;

import java.util.List;

public class AlgorithmInput {
	
	/* list of passengers and available vehicles */
	private List<Passenger> passengers;
	private List<Car> cars;
	
	/* public empty constructor */
	public AlgorithmInput() {
		super();
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "AlgorithmInput [passengers=" + passengers + ", cars=" + cars + "]";
	}

}
