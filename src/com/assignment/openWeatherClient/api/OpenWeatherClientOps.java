package com.assignment.openWeatherClient.api;

/**
 * Defines the functionalities that are provided by OpenWeatherClient
 * 
 * @author bhola
 */
public abstract class OpenWeatherClientOps {
	
	/**
	 * Defines the base URI of the Open Weather Map Client
	 */
	private final static String OPEN_WEATHER_CLIENT_URI = "http://api.openweathermap.org/";
	
	/**
	 * Defines the API key to be used for every query
	 */
	private final static String OPEN_WEATHER_CLIENT_API_KEY =
												 "&APPID=9faadc6e90e2c66cfc8d6230335b54ae";
	
	/**
	 * Gives the client base URI to be used
	 * @return
	 */
	public final static String getClientURI() {
		return OPEN_WEATHER_CLIENT_URI;
	}
	
	/**
	 * Gives the API key to be used the client
	 * @return
	 */
	public final static String getClientAPIKey() {
		return OPEN_WEATHER_CLIENT_API_KEY;
	}
	
	/*********************************** Abstract methods ****************************************/
	
	/**
	 * Returns the instance of {@link CurrentDataFetchOps}
	 */
	public abstract CurrentDataFetchOps getCurrentDataFetchOps();
	
	/**
	 * Returns the instance of {@link ForecastDataFetchOps}
	 */
	public abstract ForecastDataFetchOps getForecastDataFetchOps();
	
}
