package com.anuj.second.controller;

import com.anuj.second.entity.user;
import com.anuj.second.services.UserDetailsServiceImpl;
import com.anuj.second.services.userservice;
import com.anuj.second.utils.jwtutil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/public")
public class publiccontroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private jwtutil jwtutil;

    @Autowired
    private userservice svobj;

    @GetMapping("/Healthcheck")
    public String Healthcheck(){
        return "Ok";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody user d){
        if(svobj.save_new_user(d)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exist",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody user d) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(d.getUsername(), d.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(d.getUsername());
            String jwt=jwtutil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }
        catch(Exception e){
            log.error("**************************Error while generating token*****************************");
            return new ResponseEntity<>("Error while generating token", HttpStatus.BAD_REQUEST);
        }
    }
}
