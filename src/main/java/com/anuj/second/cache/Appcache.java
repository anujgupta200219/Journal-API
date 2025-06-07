package com.anuj.second.cache;

import com.anuj.second.entity.configentity;
import com.anuj.second.repository.configrepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Appcache {

    @Autowired
    private configrepository repo;

    public Map<String,String> appcache;

    @PostConstruct
    public void init(){
        appcache=new HashMap<>();
        List<configentity> li=repo.findAll();
        for(configentity e:li){
            appcache.put(e.getKey(),e.getValue());
        }
    }
}
