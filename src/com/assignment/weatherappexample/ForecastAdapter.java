package com.assignment.weatherappexample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.assignment.application.NApplication;
import com.assignment.openWeatherClient.model.ForecastListItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Adapter used to show the results of forecast in a list
 * 
 * @author bhola
 */
public class ForecastAdapter extends ArrayAdapter<ForecastListItem> {
	private final static String ITEM_EMPTY = "-";

	/**
	 * List of {@link ForecastListItem} to be populated in the adapter
	 */
	private List<ForecastListItem> mList;
	
	/**
	 * Inflater used to inflate the list items
	 */
	private LayoutInflater mInflator;
	
	/**
	 * Holder used to hold the item views
	 * 
	 * @author bhola
	 */
	private class ViewHolder {
		public TextView temperature;
		public TextView description;
		public TextView timestamp;
		public TextView wind;
		public TextView pressure;
		public TextView humidity;		
	}
	
	/**
	 * Constructor for {@link ForecastAdapter} 
	 * @param appContext
	 * 			Application context
	 * @param resource
	 * 			can be 0
	 * @param list
	 * 			List of items to be populated
	 */
	public ForecastAdapter(NApplication appContext, int resource, List<ForecastListItem> list) {
		super(appContext, resource, list);
		
		this.mList = list;
		this.mInflator = (LayoutInflater)appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
	}
	
	public void replaceList(List<ForecastListItem> list) {
		mList.clear();
		mList.addAll(list);
		
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		View row = null;
		
		if(null == convertView) {
			holder = new ViewHolder();
			row = mInflator.inflate(R.layout.forecast_result_list_item, parent, false);
			
			holder.temperature = (TextView)row.findViewById(R.id.current_temperature);
			holder.description = (TextView)row.findViewById(R.id.main_weather_condition);
			holder.timestamp = (TextView)row.findViewById(R.id.check_time);
			holder.wind = (TextView)row.findViewById(R.id.wind_item_value);
			holder.pressure = (TextView)row.findViewById(R.id.pressure_item_value);
			holder.humidity = (TextView)row.findViewById(R.id.humidity_item_value);
			
			row.setTag(holder);
			
		} else {
			row = convertView;
			holder = (ViewHolder)row.getTag();
		}
		
		ForecastListItem forecastListItem = mList.get(position);
		
		/* populate temperature */
		float temperature = forecastListItem.getMain().getTemp();
		holder.temperature.setText(String.valueOf(temperature) + " K");

		
		/* populate Description */
		String desription = forecastListItem.getWeather().get(0).getDescription();
		if(null != desription) {
			holder.description.setText(desription);
		} else {
			holder.description.setText(ITEM_EMPTY);	
		}
		
		/* populate Time */
		Date date = new Date(forecastListItem.getDt() * 1000L); 
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy.MM.dd, hh:mm", Locale.getDefault());
		String timestamp = dateFormat.format(date);
		holder.timestamp.setText(timestamp);
		
		/* populate wind speed */
		float windSpeed = forecastListItem.getWind().getSpeed();
		holder.wind.setText(String.valueOf(windSpeed) + " m/s");
		
		/* populate pressure value */
		int pressure = forecastListItem.getMain().getPressure();
		holder.pressure.setText(String.valueOf(pressure) + " hpa");
		
		/* populate humidity value */
		int humidity = forecastListItem.getMain().getHumidity();
		holder.humidity.setText(String.valueOf(humidity) + " %");
		
		return row;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}
}
