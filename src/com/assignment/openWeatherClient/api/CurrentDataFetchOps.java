package com.assignment.openWeatherClient.api;

import com.assignment.openWeatherClient.model.CurrentWeatherData;

/**
 * Interface that defines the APIs that can be called to get information about current weather data
 * 
 * @author bhola
 */
public interface CurrentDataFetchOps {
	
	/**
	 * Fetches the current weather data of the given location
	 * @param location
	 * 			Location for fetching weather data
	 * @return
	 */
	public CurrentWeatherData getCurrentWeatherData(String location) throws Exception;

}
