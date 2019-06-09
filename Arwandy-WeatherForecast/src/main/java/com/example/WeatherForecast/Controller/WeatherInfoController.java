package com.example.WeatherForecast.Controller;


import java.math.BigInteger;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WeatherForecast.Model.Country;
import com.example.WeatherForecast.Model.WeatherInfo;
import com.example.WeatherForecast.Repositories.CountryRepository;
import com.example.WeatherForecast.Repositories.LogRepository;
import com.example.WeatherForecast.Repositories.WeatherInfoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.BasicDBObject;

import DataLib.DateTimeEngine;
import DataLib.MongoDbFactory;
import DataLib.RestfulRetriever;
import DataLib.StringFactory;

@Controller
@RequestMapping("/")
public class WeatherInfoController {
	
	@Autowired
	WeatherInfoRepository weatherInfoRepository;
	 
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	LogRepository logRepository;
	
	MongoDbFactory DbFactory;
	
	@GetMapping("/getallweather")
	public String getAllWeatherTodayInfoFromAPI(@RequestParam(name="loc", required=false) String location,Model model) {
		/*if request parameter is same as requirement,then it will direcly
		 * return to index page. No more processing that request
		*/
		if(!location.equals("all") && !location.equals("campbell")
				&& !location.equals("omaha")&& !location.equals("austin")
				&& !location.equals("niseko")&& !location.equals("nara")
				&& !location.equals("jakarta"))
			return "index";
		
			
		
		DbFactory = new MongoDbFactory();
		
		
		/*Retrieve All Today Weather Info from dark sky API, if existed at db then
		it will skip the process */
		List<WeatherInfo> listWeatherInfo = DbFactory.getAllTodayWeatherInfo(weatherInfoRepository,countryRepository,logRepository,location);
		
		//if have new today weather data that doesn't existed at DB then save into DB
		if(listWeatherInfo.size() > 0)
			weatherInfoRepository.saveAll(listWeatherInfo);
		
		//get all weather info today from DB
		List<WeatherInfo> listWeatherInfoToday = weatherInfoRepository.findByWeatherDateBetween(DateTimeEngine.convertToTodaySODDate(),DateTimeEngine.convertToTodayEODDate());
		
		/*This conditional section is to insert all weather history data into model that will pass to view 
		 * and populate the data with thymeleaf
		 */
		if(location.equals("all")) {
			model.addAttribute("weatherObjs",weatherInfoRepository.findAll());			
		}
		else if(location.equals("campbell")) {
			model.addAttribute("weatherObjs",weatherInfoRepository.findByCountryId(new BigInteger("1")));
			listWeatherInfoToday = listWeatherInfoToday.stream().filter(o -> o.getCountryId().equals(new BigInteger("1"))).collect(Collectors.toList());
		}
		else if(location.equals("omaha")) {
			model.addAttribute("weatherObjs",weatherInfoRepository.findByCountryId(new BigInteger("2")));
			listWeatherInfoToday = listWeatherInfoToday.stream().filter(o -> o.getCountryId().equals(new BigInteger("2"))).collect(Collectors.toList());
		}
		else if(location.equals("austin")) {
			model.addAttribute("weatherObjs",weatherInfoRepository.findByCountryId(new BigInteger("3")));
			listWeatherInfoToday = listWeatherInfoToday.stream().filter(o -> o.getCountryId().equals(new BigInteger("3"))).collect(Collectors.toList());
			
		}
		else if(location.equals("niseko")) {
			model.addAttribute("weatherObjs",weatherInfoRepository.findByCountryId(new BigInteger("4")));
			listWeatherInfoToday = listWeatherInfoToday.stream().filter(o -> o.getCountryId().equals(new BigInteger("4"))).collect(Collectors.toList());
			
		}
		else if(location.equals("nara")) {
			model.addAttribute("weatherObjs",weatherInfoRepository.findByCountryId(new BigInteger("5")));
			listWeatherInfoToday = listWeatherInfoToday.stream().filter(o -> o.getCountryId().equals(new BigInteger("5"))).collect(Collectors.toList());
			
		}
		else if(location.equals("jakarta")) {
			model.addAttribute("weatherObjs",weatherInfoRepository.findByCountryId(new BigInteger("6")));
			listWeatherInfoToday = listWeatherInfoToday.stream().filter(o -> o.getCountryId().equals(new BigInteger("6"))).collect(Collectors.toList());
		}
		
		/*To add model that insert all today weather data that 
		 * will pass to view and populate the data with thymeleaf 
		 */
		model.addAttribute("weatherTodayObjs",listWeatherInfoToday);
		return "index";
	}
	
	
 
}
