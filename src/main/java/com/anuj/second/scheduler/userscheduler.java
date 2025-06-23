package com.anuj.second.scheduler;
import com.anuj.second.cache.Appcache;
import com.anuj.second.entity.entry;
import com.anuj.second.entity.user;
import com.anuj.second.services.userrepositoryImpl;
import com.anuj.second.services.emailservice;
import com.anuj.second.services.sanalysisservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class userscheduler {

    @Autowired
    private emailservice emailservice;

    @Autowired
    private userrepositoryImpl userrepository;

    @Autowired
    private sanalysisservice sanalysisservice;

    @Autowired
    private Appcache appcache;

    @Scheduled(cron = "0 0 14 * * SUN")
    public void fetchuserandsendmail(){
        List<user>users=userrepository.getuserforsa();
        for(user u:users){
//            List<String>filteredentry=u.getEntries().stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x-> x.getContent()).collect(Collectors.toList());
            List<entry>filteredentry=u.getEntries();
            String body=sanalysisservice.getsentiment(filteredentry);
            emailservice.sendEmail(u.getEmail(),"Weekly Update",body);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void refreshcache(){
        appcache.init();
    }
}
