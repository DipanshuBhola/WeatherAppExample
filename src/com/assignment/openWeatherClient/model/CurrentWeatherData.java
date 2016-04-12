package com.assignment.openWeatherClient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**	
 * Used to hold the current weather data retrieved from the server
 * 
 * @author bhola
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {
	
	/**
	 * Coordinates the geographical location
	 */
	private Coordinates coord;
	
	/**
	 * List of the detailed weather info reported
	 */
	private List<WeatherInfo> weather;
	
	/**
	 * Atmospheric variable of the location
	 */
	private AtmosphericVariables main;
	
	/**
	 * Wind conditions of the location
	 */
	private WindConditions wind;
	
	/**
	 * Time of data calculation, unix, UTC
	 */
	private long dt;
	
	/**
	 * System variables of the data
	 */
	private SystemVariables sys;
	
	/**
	 * City name of the location
	 */
	private String name;

	/**
	 * @return the coord
	 */
	public Coordinates getCoord() {
		return coord;
	}

	/**
	 * @param coord the coord to set
	 */
	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

	/**
	 * @return the weather
	 */
	public List<WeatherInfo> getWeather() {
		return weather;
	}

	/**
	 * @param weather the weather to set
	 */
	public void setWeather(List<WeatherInfo> weather) {
		this.weather = weather;
	}

	/**
	 * @return the main
	 */
	public AtmosphericVariables getMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(AtmosphericVariables main) {
		this.main = main;
	}

	/**
	 * @return the wind
	 */
	public WindConditions getWind() {
		return wind;
	}

	/**
	 * @param wind the wind to set
	 */
	public void setWind(WindConditions wind) {
		this.wind = wind;
	}

	/**
	 * @return the dt
	 */
	public long getDt() {
		return dt;
	}

	/**
	 * @param dt the dt to set
	 */
	public void setDt(long dt) {
		this.dt = dt;
	}

	/**
	 * @return the sys
	 */
	public SystemVariables getSys() {
		return sys;
	}

	/**
	 * @param sys the sys to set
	 */
	public void setSys(SystemVariables sys) {
		this.sys = sys;
	}

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
		return "CurrentWeatherData [coord=" + coord + ", weather=" + weather + ", main=" + main 
				+ ", wind=" + wind + ", dt=" + dt + ", sys=" + sys + ", name=" + name + "]";
	}

}
