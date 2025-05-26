package com.anuj.second.controller;

import com.anuj.second.entity.entry;
import com.anuj.second.entity.user;
import com.anuj.second.services.entryservice;
import com.anuj.second.services.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/controller")
public class second_controller_v2 {

    @Autowired
    private entryservice svobj;

    @Autowired
    private userservice users;

    @GetMapping
    public ResponseEntity<?> getallentriesofuser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        user u=users.findByUsername(username);
        List<entry> all=u.getEntries();
        if(all!=null && !all.isEmpty()) return new ResponseEntity<>(all,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<entry> createEntry(@RequestBody entry obj){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        try{
            svobj.save_entry(obj,username);
            return new ResponseEntity<>(obj,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<entry> getbyId(@PathVariable String myid){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        user u=users.findByUsername(username);
        List<entry> collect=u.getEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<entry> data =svobj.getById(myid);
            if(data.isPresent()){
                return new ResponseEntity<>(data.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deletebyid(@PathVariable String myid){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        svobj.deleteById(myid,username);
        return new ResponseEntity<>("Entry "+myid+" deleted",HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> updateentry(@PathVariable String myid, @RequestBody entry obj){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        user u=users.findByUsername(username);
        List<entry> collect=u.getEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            entry old = svobj.getById(myid).orElse(null);
            if (old != null) {
                old.setTitle(old.getTitle() != null && !old.getTitle().equals("") ? obj.getTitle() : old.getTitle());
                old.setContent(old.getContent() != null && !old.getContent().equals("") ? obj.getContent() : old.getTitle());
                svobj.save_entry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Entry "+myid+" not found",HttpStatus.NOT_FOUND);
    }
}
