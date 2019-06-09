package com.example.WeatherForecast.Model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="Log")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Log {

	@Field("id")
	
	private String description;
	private Date createdAt = new Date();
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "Log [description=" + description + ", createdAt=" + createdAt + "]";
	}
	
	
}
