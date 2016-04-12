package com.assignment.openWeatherClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used to represent the system values of the weather data retrieved.
 * 
 * @author bhola
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemVariables {
	
	/**
	 * Country of the location
	 */
	private String country;
	
	/**
	 * Sunrise time at the location
	 */
	private long sunrise;
	
	/**
	 * Sunset time at the location
	 */
	private long sunset;

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the sunrise
	 */
	public long getSunrise() {
		return sunrise;
	}

	/**
	 * @param sunrise the sunrise to set
	 */
	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}

	/**
	 * @return the sunset
	 */
	public long getSunset() {
		return sunset;
	}

	/**
	 * @param sunset the sunset to set
	 */
	public void setSunset(long sunset) {
		this.sunset = sunset;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordinates [country=" + country + ", sunrise=" + sunrise 
				+ ", sunset=" + sunset + "]";
	}
}
