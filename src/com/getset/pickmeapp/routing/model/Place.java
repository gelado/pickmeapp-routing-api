package com.getset.pickmeapp.routing.model;

import jsprit.core.problem.Location;

public class Place {
	
	private String placeId;
	private double y;
	private double x;
	
	public Place(double longitude, double latitude, String id) {
		super();
		coordinatesToCartesian(latitude, longitude);
		this.placeId = id;
	}
	
	public Place(Location location) {
		this.y = location.getCoordinate().getX();
		this.x = location.getCoordinate().getY();
	}
	
	/* required empty constructor */
	public Place() {
		super();
	}
	
	private void coordinatesToCartesian(double latitude, double longitude) {
		
		double earthRadius = 6371f;
		this.x = earthRadius * Math.cos(latitude) * Math.cos(longitude);
		this.y = earthRadius * Math.cos(latitude) * Math.sin(longitude);
		
	}
	
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", latitude=" + y + ", longitude=" + x + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placeId == null) ? 0 : placeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		if (placeId == null) {
			if (other.placeId != null)
				return false;
		} else if (!placeId.equals(other.placeId))
			return false;
		return true;
	}
	
}
