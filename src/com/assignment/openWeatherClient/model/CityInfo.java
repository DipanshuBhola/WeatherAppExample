package com.assignment.openWeatherClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used to represent the city information of a location where's weather information is 
 * being retrieved.
 * 
 * @author bhola
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityInfo {
	
	/**
	 * Name of the city
	 */
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CityInfo [name=" + name + "]";
	}
	
	
}
