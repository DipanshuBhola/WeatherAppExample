package com.assignment.openWeatherClient.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.assignment.openWeatherClient.api.CurrentDataFetchOps;
import com.assignment.openWeatherClient.api.OpenWeatherClientOps;
import com.assignment.openWeatherClient.model.CurrentWeatherData;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements the APIs that can be called to get information about current weather data
 * 
 * @author bhola
 */
public class CurrentDataFetchTemplate implements CurrentDataFetchOps {

	/**
	 * URI to be used to fetch current weather data
	 */
	private final static String CURRENT_WEATHER_DATA_URI = "data/2.5/weather?id=";
	
	/**
	 * Map that stores the name and city ID of the location
	 */
	private Map<String, String> mLocationInfoMap;
	
	/**
	 * Mapper used to convert JSON to {@link CurrentWeatherData}
	 */
	private ObjectMapper mMapper;
	
	/**
	 * Constructor of the CurrentDataFetchTemplate
	 * @param locationInfo
	 * 			Map that stores the name and city ID of the location
	 */
	public CurrentDataFetchTemplate(Map<String, String> locationInfo, ObjectMapper mapper) {
		this.mLocationInfoMap = locationInfo;
		this.mMapper = mapper;
	}
	
	/**
	 * Uses the Object mapper to convert the output JSON of the web service request to Current 
	 * Weather Data object
	 * 
	 * @param 
	 * 		data JSON from web service request
	 * @return
	 * 		{@link CurrentWeatherData} object
	 * 		
	 */
	private CurrentWeatherData getObject(String data) throws Exception {
		CurrentWeatherData weatherData;
		weatherData = mMapper.readValue(data, CurrentWeatherData.class);
		return weatherData;
	}

	@Override
	public CurrentWeatherData getCurrentWeatherData(String location) throws Exception {
		
		String cityID = mLocationInfoMap.get(location);
		if(null == cityID) {
			throw new IllegalStateException("API does not support this location");
		}
		
		HttpURLConnection connection = null ;
		InputStream is = null;
		String url = OpenWeatherClientOps.getClientURI() + CURRENT_WEATHER_DATA_URI + cityID 
													     + OpenWeatherClientOps.getClientAPIKey();  
		
		try {
			URL apiURL = new URL(url);
			
			connection = (HttpURLConnection)apiURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			
			StringBuffer buffer = new StringBuffer();
			is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
			}
			
			is.close();
			connection.disconnect();
			
			return getObject(buffer.toString());
			
		} catch(Exception ex) {
			ex.printStackTrace();
			
			if(null != is) {
				is.close();
			}
			if(null != connection) {
				connection.disconnect();
			}
			
			throw new RuntimeException();
		} 	
	}
}
