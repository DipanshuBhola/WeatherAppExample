package com.assignment.weatherappexample;

import java.util.ArrayList;
import java.util.List;

import com.assignment.application.NApplication;
import com.assignment.openWeatherClient.api.ForecastDataFetchOps;
import com.assignment.openWeatherClient.api.OpenWeatherClientOps;
import com.assignment.openWeatherClient.impl.OpenWeatherClientTemplate;
import com.assignment.openWeatherClient.model.ForecastListItem;
import com.assignment.openWeatherClient.model.ForecastWeatherData;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ForecastResultsActivity extends Activity {
	private final static String LOGTAG = "ForecastResultsActivity";
	
	/**
	 * Instance of the application context
	 */
	private NApplication mApplication;

	/**
	 * Spinner to show the list of all the supported values of location for the 5 days / 3 hours 
	 * forecast data
	 */
	private Spinner days5Hours3SupportSpinner;
	
	/**
	 * List of items being shown in days5Hours3SupportSpinner
	 */
	private List<String> days5Hours3SpinnerList;
	
	/**
	 * adapter used to populate the forecast data
	 */
	private ForecastAdapter forecastAdapter;
	
	/**
	 * Text view to show the lcoation
	 */
	private TextView location;

	/**
	 * Listener to be invoked whenever an item of currentWeatherSupportSpinner is clicked
	 */
	private OnItemSelectedListener days5Hours3ClickListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			String location = days5Hours3SpinnerList.get(position);
			new FetchForecastWeatherData(location).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
				
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecast_results);
		
		/* get the application context */
		mApplication = (NApplication)getApplicationContext();
		
		/* populate the days5Hours3SupportSpinner */
		days5Hours3SupportSpinner = (Spinner)findViewById(R.id.days5_hours3_support_entries);
		
		/* Create an ArrayAdapter using the string array and a default spinner layout */
		days5Hours3SpinnerList =
				new ArrayList<String>(mApplication.getLocationInfoMap().keySet());
		ArrayAdapter<String> days5Hours3SpinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, days5Hours3SpinnerList);
		
		/* Specify the layout to use when the list of choices appears */
		days5Hours3SpinnerAdapter.setDropDownViewResource(
											android.R.layout.simple_spinner_dropdown_item);
		
		/* Apply the adapter to the spinner */
		days5Hours3SupportSpinner.setAdapter(days5Hours3SpinnerAdapter);
		days5Hours3SupportSpinner.setOnItemSelectedListener(days5Hours3ClickListener);
		
		/* derefer views */
		location = (TextView)findViewById(R.id.city_name_text);
		ListView listView = (ListView)findViewById(R.id.list);
		forecastAdapter = new ForecastAdapter(mApplication, 0, new ArrayList<ForecastListItem>());
		listView.setAdapter(forecastAdapter);
	}
	
	/**
	 * Fetches the current weather data for the given location
	 * 
	 * @author bhola
	 */
	private class FetchForecastWeatherData extends AsyncTask<Void, Void, Void> {
		
		private String mLocation;
		private ForecastWeatherData weatherData;
		
		public FetchForecastWeatherData(String location) {
			this.mLocation = location;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			
			OpenWeatherClientOps openWeatherClientOps = OpenWeatherClientTemplate.getInstance();
			ForecastDataFetchOps forecastDataFetchOps = openWeatherClientOps.getForecastDataFetchOps();
			
			try {
				weatherData = forecastDataFetchOps.getForecastWeatherData(mLocation);
			} catch (Exception ex) {
				Log.e(LOGTAG, "Exception while fetching forecast weather data from "
						+ "server for location " + mLocation);
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
			
			/* show the information in UI */
			location.setText("City of " + weatherData.getCity().getName());
		
			/* populate list */
			List<ForecastListItem> list = weatherData.getList();
			forecastAdapter.replaceList(list);
		}
	}
}
