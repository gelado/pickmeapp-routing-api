package com.getset.pickmeapp.routing.model;

public class Passenger {
	
	private String requestId;
	private String carId;
	
	private Place origin;
	private Place destination;
	private long timeLeft;
	
	public Passenger(Place origin, Place destination, long timeLeft, String requestId) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.timeLeft = timeLeft;
		this.requestId = requestId;
	}
	
	/* required empty constructor */
	public Passenger() {
		super();
	}

	public Place getOrigin() {
		return origin;
	}
	public void setOrigin(Place origin) {
		this.origin = origin;
	}
	public Place getDestination() {
		return destination;
	}
	public void setDestination(Place destination) {
		this.destination = destination;
	}
	public long getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(long timeLeft) {
		this.timeLeft = timeLeft;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Override
	public String toString() {
		return "Passenger [requestId=" + requestId + ", origin=" + origin + ", destination=" + destination
				+ ", timeLeft=" + timeLeft + "]";
	}

}
