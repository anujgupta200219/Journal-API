package com.anuj.second.services;

import com.anuj.second.api_response.WeatherResponse;
import com.anuj.second.cache.Appcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weatherservice {
    @Value("${weather_api_key}")
    private String apikey;

//    private static final String api="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate resttemplate;

    @Autowired
    private Appcache app;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse wr = redisService.get("Weather_of_" + city, WeatherResponse.class);
        if(wr!=null){
            return wr;
        }else{
            String url=app.appcache.get("weather_api").replace("API_KEY",apikey).replace("CITY",city).replace(" ","%20");
            ResponseEntity<WeatherResponse> response = resttemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null) {
                redisService.set("Weather_of_" + city, body, 300l);
            }
            return body;
        }
    }

//    public WeatherResponse postweather(String city){
//        String url=app.appcache.get("weather_api").replace("API_KEY",apikey).replace("CITY",city).replace(" ","%20");
////        String d="{\n" +
////                "    \"id\":\"1\",\n" +
////                "    \"username\":\"Anuj\",\n" +
////                "    \"password\":\"Anuj\"\n" +
////                "}";
////        HttpEntity<String> httpEntity=new HttpEntity<>(d);
//
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.set("key","value");
//
//        UserDetails user = User.builder().username("anuj").password("anuj").build();
//        HttpEntity<UserDetails>httpEntity=new HttpEntity<>(user,httpHeaders);
//        ResponseEntity<WeatherResponse> response=resttemplate.exchange(url,HttpMethod.POST,httpEntity,WeatherResponse.class);
//        return response.getBody();
//    }
}
