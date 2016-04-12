package com.assignment.openWeatherClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used to represent the wind conditions of the weather data retrieved.
 * 
 * @author bhola
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindConditions {
	
	/**
	 * Wind speed value
	 */
	private float speed;
	
	/**
	 * Wind direction, degrees (meteorological)
	 */
	private int deg;

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * @return the deg
	 */
	public int getDeg() {
		return deg;
	}

	/**
	 * @param deg the deg to set
	 */
	public void setDeg(int deg) {
		this.deg = deg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WindConditions [speed=" + speed + ", deg=" + deg + "]";
	}

}
