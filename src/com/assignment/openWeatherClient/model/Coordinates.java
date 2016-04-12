package com.assignment.openWeatherClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used to represent the geographical coordinates of  a location where's weather information is 
 * being retrieved.
 * 
 * @author bhola
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
	
	/**
	 * Longitude of the location
	 */
	private float lon;
	
	/**
	 * Latitude of the location
	 */
	private float lat;

	/**
	 * @return the lon
	 */
	public float getLon() {
		return lon;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(float lon) {
		this.lon = lon;
	}

	/**
	 * @return the lat
	 */
	public float getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(float lat) {
		this.lat = lat;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordinates [lon=" + lon + ", lat=" + lat + "]";
	}
}
