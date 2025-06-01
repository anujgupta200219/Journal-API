package com.anuj.second.controller;

import com.anuj.second.api_response.WeatherResponse;
import com.anuj.second.entity.user;
import com.anuj.second.repository.userrepository;
import com.anuj.second.services.userservice;
import com.anuj.second.services.weatherservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class user_controller {

    @Autowired
    private userservice svobj;

    @Autowired
    private userrepository repo;

    @Autowired
    private weatherservice ws;

    @PutMapping
    public ResponseEntity<?> edituser(@RequestBody user d){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        user u = svobj.findByUsername(d.getUsername());
        u.setUsername(d.getUsername());
        u.setPassword(d.getPassword());
        svobj.save_new_user(u);
        return new ResponseEntity<>("Updated",HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteuser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        repo.deleteByusername(auth.getName());
        return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = ws.getWeather("Mumbai");
        String greet="";
        if(weatherResponse != null){
            greet=" , Todays Weather feels like: "+weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+auth.getName() + greet,HttpStatus.OK);
    }

}
