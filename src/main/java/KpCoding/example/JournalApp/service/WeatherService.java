package KpCoding.example.JournalApp.service;

import KpCoding.example.JournalApp.Cache.AppCache;
import KpCoding.example.JournalApp.Constants.PlaceHolders;
import KpCoding.example.JournalApp.api.resoponse.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {
    @Value("${weather.api.key}")
    private String API_KEY;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String apiTemplate = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString());
        if(apiTemplate == null){
            log.error("WEATHER_API does not found, check DB");
        }
        assert apiTemplate != null;
        String finalApi = apiTemplate.replace(PlaceHolders.CITY,city).replace(PlaceHolders.API_KEY,API_KEY);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
        return response.getBody();
    }

}
