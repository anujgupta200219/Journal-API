package com.anuj.second.controller;

import com.anuj.second.entity.user;
import com.anuj.second.services.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publiccontroller {

    @Autowired
    private userservice svobj;

    @GetMapping("/Healthcheck")
    public String Healthcheck(){
        return "Ok";
    }

    @PostMapping("/create_user")
    public ResponseEntity<?> saveuser(@RequestBody user d){
        if(svobj.save_new_user(d)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exist",HttpStatus.BAD_REQUEST);
        }
    }
}
