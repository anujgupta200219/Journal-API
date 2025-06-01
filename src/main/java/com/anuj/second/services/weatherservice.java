package com.anuj.second.services;

import com.anuj.second.api_response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weatherservice {
    private static final String apikey="00068b162b91a697785c699239b0c029";
    private static final String api="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate resttemplate;

    public WeatherResponse getWeather(String city){
        String url=api.replace("API_KEY",apikey).replace("CITY",city).replace(" ","%20");
        ResponseEntity<WeatherResponse> response = resttemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}
