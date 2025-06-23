package com.anuj.second.services;

import com.anuj.second.entity.entry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class sanalysisservice {

    public String getsentiment(List<entry>entries){
        int i=1;
        String output="";
        for(entry e:entries){
            output=output+"\n"+i+". "+e.getContent();
            i++;
        }
        return output;
    }
}