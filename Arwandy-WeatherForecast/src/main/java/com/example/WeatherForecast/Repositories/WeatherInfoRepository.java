package com.example.WeatherForecast.Repositories;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.WeatherForecast.Model.Country;
import com.example.WeatherForecast.Model.WeatherInfo;

@Repository
public interface  WeatherInfoRepository extends MongoRepository<WeatherInfo, String> {
	WeatherInfo findByCountryIdAndWeatherDate(BigInteger CountryId,Date weatherDate);
	List<WeatherInfo> findByCountryId(BigInteger CountryId);
	Long deleteByCreatedAtLessThan(Date dt);
	Long deleteByCountryId(BigInteger CountryId);
	List<WeatherInfo> findByWeatherDateLessThan(Date dt);
	List<WeatherInfo> findByWeatherDateBetween(Date startdt,Date endDt);
}
