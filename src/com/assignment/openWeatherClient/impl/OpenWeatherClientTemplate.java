package com.assignment.openWeatherClient.impl;

import java.util.Map;

import com.assignment.openWeatherClient.api.CurrentDataFetchOps;
import com.assignment.openWeatherClient.api.ForecastDataFetchOps;
import com.assignment.openWeatherClient.api.OpenWeatherClientOps;
import com.assignment.openWeatherClient.model.CurrentWeatherData;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements the functionalities that are provided by OpenWeatherClient
 * 
 * @author bhola
 */
public class OpenWeatherClientTemplate extends OpenWeatherClientOps {
	
	/**
	 * Singleton instance of the {@link OpenWeatherClientOps}
	 */
	private static volatile OpenWeatherClientOps mInstance;
	
	/**
	 * Used to fetch {@link CurrentWeatherData} info
	 */
	private CurrentDataFetchOps mCurrentDataFetchOps;
	
	/**
	 * Used to fetch {@link ForecastWeatherData} info
	 */
	private ForecastDataFetchOps mForecastDataFetchOps;
	
	/**
	 * Constructor for {@link OpenWeatherClientTemplate}
	 * @param map
	 * @param mapper
	 */
	private OpenWeatherClientTemplate(Map<String, String> map,
			   ObjectMapper mapper) {
		mCurrentDataFetchOps = new CurrentDataFetchTemplate(map, mapper);
		mForecastDataFetchOps = new ForecastDataFetchTemplate(map, mapper);
	}
	
	/**
	 * Create a single instance of the {@link OpenWeatherClientTemplate}
	 * @param map
	 * 			Map used to get City ID from location
	 * @param mapper
	 * 			Instance of objectMapper
	 * @return
	 * 		Singleton instance of {@link OpenWeatherClientTemplate}
	 */
	public static OpenWeatherClientOps createOpenWeatherClientOps(Map<String, String> map,
															   ObjectMapper mapper) {
		if(null == mInstance) {
			synchronized(OpenWeatherClientTemplate.class) {
				if(null == mInstance) {
					mInstance = new OpenWeatherClientTemplate(map, mapper);
				}
			}
		}
		
		return mInstance;
	}
	
	/**
	 * Gets the instance of this class
	 * @return
	 */
	public static OpenWeatherClientOps getInstance() {
		return mInstance;
	}
	
	/*********************************** Abstract methods ****************************************/
	@Override
	public CurrentDataFetchOps getCurrentDataFetchOps() {
		return mCurrentDataFetchOps;
	}

	@Override
	public ForecastDataFetchOps getForecastDataFetchOps() {
		return mForecastDataFetchOps;
	}	
	
}
