package com.assignment.openWeatherClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used to represent the more weather info of the weather data retrieved.
 * 
 * @author bhola
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {
	
	/**
	 * Main weather condition
	 */
	private String main;
	
	/**
	 * Description of weather conditions
	 */
	private String description;
	
	/**
	 * Weather icon id
	 */
	private String icon;

	/**
	 * @return the main
	 */
	public String getMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(String main) {
		this.main = main;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WeatherInfo [main=" + main + ", description=" + description 
				+ ", icon=" + icon + "]";
	}
}
