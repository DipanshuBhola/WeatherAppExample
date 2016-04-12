package com.assignment.openWeatherClient.api;

import com.assignment.openWeatherClient.model.ForecastWeatherData;

/**
 * Interface that defines the APIs that can be called to get information about 5 days / 3 hours data
 * forecast
 * 
 * @author bhola
 */
public interface ForecastDataFetchOps {
	
	/**
	 * Fetches the forecast weather data of the given location
	 * @param location
	 * 			Location for fetching weather data
	 * @return
	 */
	public ForecastWeatherData getForecastWeatherData(String location) throws Exception;

}
