package com.assignment.openWeatherClient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**	
 * Used to hold the 5 days / 3 hours weather forecast data retrieved from the server
 * 
 * @author bhola
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastWeatherData {
	
	/**
	 * Information about the city info of the location
	 */
	private CityInfo city;
	
	/**
	 * Coordinates the geographical location
	 */
	private Coordinates coord;
	
	/**
	 * Country of the location
	 */
	private String country;
	
	/**
	 * List of weather conditions
	 */
	private List<ForecastListItem> list;

	/**
	 * @return the city
	 */
	public CityInfo getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(CityInfo city) {
		this.city = city;
	}

	/**
	 * @return the coord
	 */
	public Coordinates getCoord() {
		return coord;
	}

	/**
	 * @param coord the coord to set
	 */
	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the list
	 */
	public List<ForecastListItem> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<ForecastListItem> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ForecastWeatherData [city=" + city + ", coord=" + coord
				+ ", country=" + country + ", list=" + list + "]";
	}
	
}
