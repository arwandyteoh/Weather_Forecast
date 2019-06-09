package com.example.WeatherForecast.Model;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Document(collection="WeatherInfo")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class WeatherInfo {
	
	@Field("id")
	
	private BigInteger countryId;
	
	private String countryName;

	private String summary;
	
	private String icon;
	
	private double windSpeed;
	
	private double tempMin;
	
	private double tempMax;
	
	private double humidity;
	
	private Date weatherDate = new Date();
	
	private Date createdAt = new Date();

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public Date getWeatherDate() {
		return weatherDate;
	}

	public void setWeatherDate(Date weatherDate) {
		this.weatherDate = weatherDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public BigInteger getCountryId() {
		return countryId;
	}

	public void setCountryId(BigInteger countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	@Override
	public String toString() {
		return "WeatherInfo [countryId=" + countryId + ", countryName=" + countryName + ", summary=" + summary + ", icon="
				+ icon + ", windSpeed=" + windSpeed + ", tempMin=" + tempMin + ", tempMax=" + tempMax + ", humidity="
				+ humidity + ", weatherDate=" + weatherDate + ", createdAt=" + createdAt + "]";
	}



	
	
	
}
