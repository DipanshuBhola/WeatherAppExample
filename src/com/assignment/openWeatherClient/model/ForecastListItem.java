package com.assignment.openWeatherClient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used to represent the 5 days/ 3 hours weather information
 * 
 * @author bhola
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastListItem {
	
	/**
	 * Time of data calculation, unix, UTC
	 */
	private long dt;
	
	/**
	 * Atmospheric variable of the location
	 */
	private AtmosphericVariables main;
	
	/**
	 * List of the detailed weather info reported
	 */
	private List<WeatherInfo> weather;
	
	/**
	 * Wind conditions of the location
	 */
	private WindConditions wind;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ForecastListItem [dt=" + dt + ", main=" + main + ", weather="
				+ weather + ", wind=" + wind + "]";
	}

}
