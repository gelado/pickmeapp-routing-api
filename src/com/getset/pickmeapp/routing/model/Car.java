package com.getset.pickmeapp.routing.model;

public class Car {
	
	private String id;
	private Place coordinate;
	
	public Car() {
		super();
	}

	public Car(String id, Place coordinate) {
		super();
		this.id = id;
		this.coordinate = coordinate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Place getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Place coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", coordinate=" + coordinate + "]";
	}
}
