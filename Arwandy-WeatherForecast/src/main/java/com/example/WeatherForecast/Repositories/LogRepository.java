package com.example.WeatherForecast.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.WeatherForecast.Model.Log;

public interface LogRepository extends MongoRepository<Log, String>{

}
