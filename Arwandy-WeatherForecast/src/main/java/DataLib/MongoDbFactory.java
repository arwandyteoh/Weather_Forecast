package DataLib;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.WeatherForecast.Model.Country;
import com.example.WeatherForecast.Model.Log;
import com.example.WeatherForecast.Model.WeatherInfo;
import com.example.WeatherForecast.Repositories.CountryRepository;
import com.example.WeatherForecast.Repositories.LogRepository;
import com.example.WeatherForecast.Repositories.WeatherInfoRepository;
import com.fasterxml.jackson.databind.JsonNode;

public class MongoDbFactory {
	
	//Method to get all weather information base from parameter reques (location)
	public List<WeatherInfo> getAllTodayWeatherInfo(WeatherInfoRepository weatherInfoRepository,CountryRepository countryRepository,LogRepository logRepository,String location){
		List<WeatherInfo> listWeatherInfo = new ArrayList<WeatherInfo>();
		List<WeatherInfo> weatherTodayList = new ArrayList<WeatherInfo>();
		BigInteger countryId;
		JsonNode rootNode;
		String url;
		Country co;
		
		//Delete all 3 days old data base from created date
		this.cleansingAll3DaysOldData(weatherInfoRepository);
		
		//get End of Day Today Timestamp (ie: 08 June 2019 23:59:59)
		String millisToday = DateTimeEngine.convertToTodayEODTimeStamp();
		
		//find all today weather's location data from DB 
		weatherTodayList = weatherInfoRepository.findByWeatherDateBetween(DateTimeEngine.convertToTodaySODDate(), DateTimeEngine.convertToTodayEODDate());
		
		//Campbell, CA
		if(location.equals("all") || location.equals("campbell")) {
			url = MessageFormat.format("{0}{1}/{2}{3}", StringFactory.baseUrl,StringFactory.darkSkyKey,StringFactory.longLatCampbell +","+millisToday,StringFactory.urlParam);;
		    
			//found country id from table Country
			co = countryRepository.findFirstByCountry("Campbell, CA");
			countryId = co != null ? co.getid() : new BigInteger("0");
			
			/*check if today weather already existed at db
			 * if existed, then skip retrieve from dark sky api 
			 * if not, then retrieve from dark sky api
			 * */
			
			if(!this.containsWeatherData(weatherTodayList,countryId)) {				
				//get json data from url API 
				rootNode = RestfulRetriever.RetrieveFromURL(url);
				//create log request api
				MongoDbFactory.createLog(logRepository,"Request URL API from " +url);
				//add all today weather data into list object
				listWeatherInfo.addAll(RestfulRetriever.parsingDailyWeatherInfo(rootNode, co, weatherInfoRepository));
			}
		}
		
		//Omaha, NE
		if(location.equals("all") || location.equals("omaha")) {
			url = MessageFormat.format("{0}{1}/{2}{3}", StringFactory.baseUrl,StringFactory.darkSkyKey,StringFactory.longLatOmaha +","+millisToday,StringFactory.urlParam);;		
			
			//found country id from table Country
			co = countryRepository.findFirstByCountry("Omaha, NE");
			countryId = co != null ? co.getid() : new BigInteger("0");
			
			/*check if today weather already existed at db
			 * if existed, then skip retrieve from dark sky api 
			 * if not, then retrieve from dark sky api
			 * */
			if(!this.containsWeatherData(weatherTodayList,countryId)) {
				//get json data from url API 
				rootNode = RestfulRetriever.RetrieveFromURL(url);
				//create log request api
				MongoDbFactory.createLog(logRepository,"Request URL API from " +url);
				//add all today weather data into list object
				listWeatherInfo.addAll(RestfulRetriever.parsingDailyWeatherInfo(rootNode, co, weatherInfoRepository));
			}
		}
		
		//Austin, TX
		if(location.equals("all") || location.equals("austin")) {
			url = MessageFormat.format("{0}{1}/{2}{3}", StringFactory.baseUrl,StringFactory.darkSkyKey,StringFactory.longLatAustin +","+millisToday,StringFactory.urlParam);;
			//found country id from table Country
			co = countryRepository.findFirstByCountry("Austin, TX");
			countryId = co != null ? co.getid() : new BigInteger("0");
			
			/*check if today weather already existed at db
			 * if existed, then skip retrieve from dark sky api
			 * if not, then retrieve from dark sky api
			 * */
			if(!this.containsWeatherData(weatherTodayList,countryId)) {
				//get json data from url API 
				rootNode = RestfulRetriever.RetrieveFromURL(url);
				//create log request api
				MongoDbFactory.createLog(logRepository,"Request URL API from " +url);
				//add all today weather data into list object
				listWeatherInfo.addAll(RestfulRetriever.parsingDailyWeatherInfo(rootNode, co, weatherInfoRepository));
			}
		}
		
		//Niseko, Japan
		if(location.equals("all") || location.equals("niseko")) {
			url = MessageFormat.format("{0}{1}/{2}{3}", StringFactory.baseUrl,StringFactory.darkSkyKey,StringFactory.longLatNiseko +","+millisToday,StringFactory.urlParam);;		
			//found country id from table Country
			co = countryRepository.findFirstByCountry("Niseko, Japan");
			countryId = co != null ? co.getid() : new BigInteger("0");
			
			/*check if today weather already existed at db
			 * if existed, then skip retrieve from dark sky api
			 * if not, then retrieve from dark sky api
			 * */
			if(!this.containsWeatherData(weatherTodayList,countryId)) {
				//get json data from url API
				rootNode = RestfulRetriever.RetrieveFromURL(url);
				//create log request api
				MongoDbFactory.createLog(logRepository,"Request URL API from " +url);
				//add all today weather data into list object
				listWeatherInfo.addAll(RestfulRetriever.parsingDailyWeatherInfo(rootNode, co, weatherInfoRepository));
			}
		}
		
		//Nara, Japan
		if(location.equals("all") || location.equals("nara")) {
			url = MessageFormat.format("{0}{1}/{2}{3}", StringFactory.baseUrl,StringFactory.darkSkyKey,StringFactory.longLatNara +","+millisToday,StringFactory.urlParam);;
			
			//found country id from table Country
			co = countryRepository.findFirstByCountry("Nara, Japan");
			countryId = co != null ? co.getid() : new BigInteger("0");
			
			/*check if today weather already existed at db
			 * if existed, then skip retrieve from dark sky api
			 * if not, then retrieve from dark sky api
			 * */
			if(!this.containsWeatherData(weatherTodayList,countryId)) {
				//get json data from url API
				rootNode = RestfulRetriever.RetrieveFromURL(url);	
				//create log request api
				MongoDbFactory.createLog(logRepository,"Request URL API from " +url);
				//add all today weather data into list object
				listWeatherInfo.addAll(RestfulRetriever.parsingDailyWeatherInfo(rootNode, co, weatherInfoRepository));
			}
		}
		
		//Jakarta, Indonesia
		if(location.equals("all") || location.equals("jakarta")) {
			url = MessageFormat.format("{0}{1}/{2}{3}", StringFactory.baseUrl,StringFactory.darkSkyKey,StringFactory.longLatJakarta +","+millisToday,StringFactory.urlParam);;	
			
			//found country id from table Country
			co = countryRepository.findFirstByCountry("Jakarta, Indonesia");
			countryId = co != null ? co.getid() : new BigInteger("0");
			
			/*check if today weather already existed at db
			 * if existed, then skip retrieve from dark sky api
			 * if not, then retrieve from dark sky api
			 * */
			if(!this.containsWeatherData(weatherTodayList,countryId)) {
				//get json data from url API
				rootNode = RestfulRetriever.RetrieveFromURL(url);	
				//create log request api
				MongoDbFactory.createLog(logRepository,"Request URL API from " +url);
				//add all today weather data into list object
				listWeatherInfo.addAll(RestfulRetriever.parsingDailyWeatherInfo(rootNode, co, weatherInfoRepository));
			}
		}
		return listWeatherInfo;

	}
	
	private void cleansingAll3DaysOldData(WeatherInfoRepository weatherInfoRepository) {
		LocalDateTime ldt = LocalDateTime.now().minusDays(3);
		Date dt = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		
		Long wthinf = weatherInfoRepository.deleteByCreatedAtLessThan(dt);

	}
	
	private boolean containsWeatherData(final List<WeatherInfo> list, final BigInteger Id){
	    return list.stream().filter(o -> o.getCountryId().equals(Id)).findFirst().isPresent();
	}
	
	public static void createLog(LogRepository logRepository,String message) {
		Log log = new Log();
		log.setDescription(message);
		
		logRepository.save(log);
	}
}
