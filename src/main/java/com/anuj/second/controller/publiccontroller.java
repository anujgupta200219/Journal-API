package com.anuj.second.controller;

import com.anuj.second.entity.user;
import com.anuj.second.services.userservice;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveuser(@RequestBody user d){
        svobj.save_new_user(d);
    }
}
