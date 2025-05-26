//package com.anuj.second.controller;
//import com.anuj.second.entity.entry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//@RestController
//@RequestMapping("/controllers")
//public class second_controller {
//
//    public Map<String, entry>entries=new HashMap<>();
//
//    @GetMapping
//    public List<entry>getall(){
//        return new ArrayList<>(entries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody entry obj){
//        entries.put(obj.getId(),obj);
//        return true;
//    }
//
//    @GetMapping("id/{myid}")
//    public entry getbyId(@PathVariable String myid){
//        return entries.get(myid);
//    }
//    @DeleteMapping("id/{myid}")
//    public entry deletebyid(@PathVariable String myid){
//        return entries.remove(myid);
//    }
//
//    @PutMapping("id/{myid}")
//    public entry updateentry(@PathVariable String myid,@RequestBody entry obj){
//        return entries.put(myid,obj);
//    }
//}
