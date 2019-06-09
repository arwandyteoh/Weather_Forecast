package com.example.WeatherForecast.Model;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Country")
public class Country {
	
	@Field("id")
	private BigInteger id;
	
	private String country;

	public BigInteger getid() {
		return id;
	}

	public void setid(BigInteger id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		country = country;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", Country=" + country + "]";
	}
	
	
}
