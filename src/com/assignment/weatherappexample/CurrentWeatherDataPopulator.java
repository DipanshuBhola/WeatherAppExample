package com.assignment.weatherappexample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.assignment.application.NApplication;
import com.assignment.openWeatherClient.model.CurrentWeatherData;
import com.assignment.openWeatherClient.model.WeatherInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This class takes the responsibility of populating the current weather data in the right form.
 * The placement of the UI is defined by activity and is passed here as a container. What and how 
 * things show in container is responsibility of this class.
 * 
 * This helps decoupling of fetching and populating data
 * 
 * @author bhola
 */
public class CurrentWeatherDataPopulator {
	
	/**
	 * TextView to show Location for which weather data is shown
	 */
	private TextView mLocation;
	
	/**
	 * TextView to show Temperature of the location
	 */
	private TextView mTemperature;
	
	/**
	 * TextView to show weather description
	 */
	private TextView mDescription;
	
	/**
	 * TextView to show time of calculations
	 */
	private TextView mTime;
	
	/**
	 * TextView to show wind speed at the location
	 */
	private TextView mWindSpeed;
	
	/**
	 * TextView to show pressure at the location
	 */
	private TextView mPressure;
	
	/**
	 * TextView to show humidity of the location
	 */
	private TextView mHumidity;
	
	/**
	 * TextView to show sunrise time
	 */
	private TextView mSunriseTime;
	
	/**
	 * TextView to show sunset time
	 */
	private TextView mSunsetTime;
	
	/**
	 * Constructor of this class
	 * @param appContext
	 * 			Application context, used to inflate view
	 * @param container
	 * 			Tells where to add the view
	 */
	public CurrentWeatherDataPopulator(NApplication appContext, ViewGroup container) {
		
		/* get the inflater to inflate the layout and add that layout to container */
		LayoutInflater inflater =
				(LayoutInflater)appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.show_results_layout, container);
		
		/* derefer all the views */
		mLocation = (TextView)view.findViewById(R.id.city_name_text);
		mTemperature = (TextView)view.findViewById(R.id.current_temperature);
		mDescription = (TextView)view.findViewById(R.id.main_weather_condition);
		mTime = (TextView)view.findViewById(R.id.check_time);
		mWindSpeed = (TextView)view.findViewById(R.id.wind_item_value);
		mPressure = (TextView)view.findViewById(R.id.pressure_item_value);
		mHumidity = (TextView)view.findViewById(R.id.humidity_item_value);
		mSunriseTime = (TextView)view.findViewById(R.id.sunrise_item_value);
		mSunsetTime = (TextView)view.findViewById(R.id.sunset_item_value);
		
	}
	
	/**
	 * Populates the views with the given parameters
	 * @param weatherData
	 * 			Fetched value of weather data
	 */
	public void populateView(CurrentWeatherData weatherData) {
		
		/* populate location */
		mLocation.setText("City of " + weatherData.getName() + ", " + weatherData.getSys().getCountry());
		
		/* populate temperature */
		mTemperature.setText(String.valueOf(weatherData.getMain().getTemp()) + " K");
		
		/* populate description */
		if(weatherData.getWeather().size() != 0) {
			WeatherInfo info = weatherData.getWeather().get(0);
			mDescription.setText(info.getDescription());
		}
		
		/* populate time get at 2016.04.11 22:48*/
		Date date = new Date(weatherData.getDt() * 1000L); 
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy.MM.dd, hh:mm", Locale.getDefault());
		String timestamp = dateFormat.format(date);
		mTime.setText("get at " + timestamp);
		
		/* populate wind speed */
		mWindSpeed.setText("Speed of " + weatherData.getWind().getSpeed() + "m/s " 
							+ "(" + weatherData.getWind().getDeg() + ")");
		
		/* populate pressure */
		mPressure.setText(String.valueOf(weatherData.getMain().getPressure()) + " hpa");
		
		/* populate humidity */
		mHumidity.setText(String.valueOf(weatherData.getMain().getHumidity()) + " %");
		
		/* set sunrise and sunset */
		SimpleDateFormat dateFormat1;
		dateFormat1 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
		
		Date sunriseDate = new Date(weatherData.getSys().getSunrise() * 1000L);
		String sunrise = dateFormat1.format(sunriseDate);
		mSunriseTime.setText(sunrise);
		
		Date sunsetDate = new Date(weatherData.getSys().getSunset() * 1000L);
		String sunset = dateFormat1.format(sunsetDate);
		mSunsetTime.setText(sunset);

	}
}
