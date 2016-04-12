package com.assignment.application;

import java.util.HashMap;
import java.util.Map;

import com.assignment.openWeatherClient.impl.OpenWeatherClientTemplate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Application;

/**
 * Application context of the application. This class is the first thing getting initialised in the
 * application 
 * 
 * @author bhola
 */
public class NApplication extends Application {
	
	/**
	 * Map containing the locations names and the corresponding City IDs of those locations which  
	 * are the supported by the application
	 */
	private Map<String, String> mLocationInfoMap;
	
	/**
	 * Creates the LocationInfoMap instance and stores the supported values
	 */
	private void createLocationInfoMap() {
		mLocationInfoMap = new HashMap<String, String>();
		
		/* add supported entries in the MAP */
		mLocationInfoMap.put("London,GB", "2643743");
		mLocationInfoMap.put("Tokyo,JP", "1850147");
		mLocationInfoMap.put("Delhi,IN", "1273294");
		mLocationInfoMap.put("Bangalore,IN", "1277333");
		mLocationInfoMap.put("Ambala,IN", "1278860");
		mLocationInfoMap.put("Paris,FR", "2968815");
		mLocationInfoMap.put("New York,US", "5106292");
		mLocationInfoMap.put("Detroit,US", "4990729");
		mLocationInfoMap.put("Sydney,CA", "6160752");
		mLocationInfoMap.put("Beijing,CN", "1816670");
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		/* create Location info map */
		createLocationInfoMap();
		
		/* create the Object Mapper */
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		/* create the instance of OpenWeatherClientOps. Creating the single instance of the class */
		/* up-front so that NApplication can take control of the LocationInfoMap being supplied   */ 
		/* of the instance 																		  */
		OpenWeatherClientTemplate.createOpenWeatherClientOps(mLocationInfoMap, mapper);
	}
	
	/**
	 * Returns the location info map
	 * @return
	 */
	public Map<String, String> getLocationInfoMap() {
		return mLocationInfoMap;
	}

}
