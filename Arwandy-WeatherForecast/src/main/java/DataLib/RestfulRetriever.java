package DataLib;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.example.WeatherForecast.Model.Country;
import com.example.WeatherForecast.Model.WeatherInfo;
import com.example.WeatherForecast.Repositories.CountryRepository;
import com.example.WeatherForecast.Repositories.WeatherInfoRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestfulRetriever {
	
	//method to getJsonFromURL
	public static JsonNode  RetrieveFromURL(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory factory = mapper.getJsonFactory();
		
		String response = restTemplate.getForObject(url, String.class);
		JsonNode rootNode = null;
		
		try {
			rootNode = mapper.readTree(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rootNode;
	}
	
	//Method to parsing JSON into object
	public static List<WeatherInfo> parsingDailyWeatherInfo(JsonNode nodeJson,Country co,WeatherInfoRepository weatherInfoRepository ){
		List<WeatherInfo> listWeatherInfo = new ArrayList<WeatherInfo>();
		
		JsonNode node = (nodeJson.get("daily")).get("data");
		
		String temperature = "";
		for (JsonNode jsonNode : node) {
			WeatherInfo weatherObj = new WeatherInfo();
			if(jsonNode.get("icon") != null)
				weatherObj.setIcon(jsonNode.get("icon").asText());
			if(jsonNode.get("humidity") != null)
				weatherObj.setHumidity(Double.parseDouble(jsonNode.get("humidity").asText()));
			if(jsonNode.get("summary") != null)
				weatherObj.setSummary(jsonNode.get("summary").asText());
			if(jsonNode.get("temperatureMax") != null)
				weatherObj.setTempMax(Double.parseDouble(jsonNode.get("temperatureMax").asText()));
			if(jsonNode.get("temperatureMin") != null)
				weatherObj.setTempMin(Double.parseDouble(jsonNode.get("temperatureMin").asText()));
			if(jsonNode.get("windSpeed") != null)
				weatherObj.setWindSpeed(Double.parseDouble(jsonNode.get("windSpeed").asText()));
			if(jsonNode.get("time") != null)
				weatherObj.setWeatherDate(new Date(Long.parseLong(jsonNode.get("time").asText())*1000));	
				
			if(co != null) {
				weatherObj.setCountryName(co.getCountry());
				weatherObj.setCountryId(co.getid());
			}
						
			listWeatherInfo.add(weatherObj);
		}
		
		return listWeatherInfo;
	}

}
