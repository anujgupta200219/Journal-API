package com.anuj.second.controller;

import com.anuj.second.entity.user;
import com.anuj.second.services.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class admincontroller {

    @Autowired
    private userservice svobj;

    @GetMapping("/all-user")
    public  ResponseEntity<?> getallusers(){
        List<user> all=svobj.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public ResponseEntity<?> create_admin(@RequestBody user d){
        try{
            svobj.save_admin(d);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
