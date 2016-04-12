package com.assignment.weatherappexample;

import java.util.ArrayList;
import java.util.List;

import com.assignment.application.NApplication;
import com.assignment.openWeatherClient.api.CurrentDataFetchOps;
import com.assignment.openWeatherClient.api.OpenWeatherClientOps;
import com.assignment.openWeatherClient.impl.OpenWeatherClientTemplate;
import com.assignment.openWeatherClient.model.CurrentWeatherData;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class WeatherResultsActivity extends Activity {
	private final static String LOGTAG = "WeatherResultsActivity";
	
	/**
	 * Instance of the application context
	 */
	private NApplication mApplication;

	/**
	 * Spinner to show the list of all the supported values of location for the current weather
	 * data
	 */
	private Spinner currentWeatherSupportSpinner;
	
	/**
	 * List of items being shown in currentWeatherSupportSpinner
	 */
	private List<String> currentWeatherSpinnerList;
	
	/**
	 * Populates the fetched current weather data to the views
	 */
	private CurrentWeatherDataPopulator currentWeatherDataPopulator;
	
	/**
	 * Listener to be invoked whenever an item of currentWeatherSupportSpinner is clicked
	 */
	private OnItemSelectedListener currentWeatherClickListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			String location = currentWeatherSpinnerList.get(position);
			new FetchCurrentWeatherData(location).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
				
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_results);
		
		/* get the application context */
		mApplication = (NApplication)getApplicationContext();
		
		/* create current weather data view populater */
		ViewGroup currentWeatherDataContainer = 
				(ViewGroup)findViewById(R.id.current_weather_data_view);
		currentWeatherDataPopulator = 
				new CurrentWeatherDataPopulator(mApplication, currentWeatherDataContainer);
		
		/* populate the currentWeatherSupportSpinner */
		currentWeatherSupportSpinner = (Spinner)findViewById(R.id.current_weather_support_entries);
		
		/* Create an ArrayAdapter using the string array and a default spinner layout */
		currentWeatherSpinnerList =
				new ArrayList<String>(mApplication.getLocationInfoMap().keySet());
		ArrayAdapter<String> currentWeatherSpinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, currentWeatherSpinnerList);
		
		/* Specify the layout to use when the list of choices appears */
		currentWeatherSpinnerAdapter.setDropDownViewResource(
											android.R.layout.simple_spinner_dropdown_item);
		
		/* Apply the adapter to the spinner */
		currentWeatherSupportSpinner.setAdapter(currentWeatherSpinnerAdapter);
		currentWeatherSupportSpinner.setOnItemSelectedListener(currentWeatherClickListener);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			
		case R.id.action_show_forecast:			
			Intent intent = new Intent(this, ForecastResultsActivity.class);
			startActivity(intent);
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Fetches the current weather data for the given location
	 * 
	 * @author bhola
	 */
	private class FetchCurrentWeatherData extends AsyncTask<Void, Void, Void> {
		
		private String mLocation;
		private CurrentWeatherData weatherData;
		
		public FetchCurrentWeatherData(String location) {
			this.mLocation = location;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			
			OpenWeatherClientOps openWeatherClientOps = OpenWeatherClientTemplate.getInstance();
			CurrentDataFetchOps currentDataFetchOps = openWeatherClientOps.getCurrentDataFetchOps();
			
			try {
				weatherData = currentDataFetchOps.getCurrentWeatherData(mLocation);
			} catch (Exception ex) {
				Log.e(LOGTAG, "Exception while fetching current weather data from "
						+ "server for location" + mLocation);
				ex.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			if(null == weatherData) {				
				/* show the failure message on the screen */
				Toast.makeText(mApplication, 
							  mApplication.getResources().getString(R.string.failure_message),
							  Toast.LENGTH_SHORT).show();				
				return;
			}
			
			/* show the informaiton in UI */
			Log.d(LOGTAG, "current weather data fetched: " + weatherData);
			currentWeatherDataPopulator.populateView(weatherData);
		}
	}
}
