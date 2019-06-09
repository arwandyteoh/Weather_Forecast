package com.example.WeatherForecast.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.WeatherForecast.Model.Country;

public interface CountryRepository  extends MongoRepository<Country, String> {
//	@Query("{Country:/?0/}")
	Country findFirstByCountry(String Country);
}
