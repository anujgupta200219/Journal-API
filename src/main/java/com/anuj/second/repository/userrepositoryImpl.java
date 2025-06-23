package com.anuj.second.repository;

import com.anuj.second.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class userrepositoryImpl {



    @Autowired
    private MongoTemplate mongoTemplate;

    public List<user> getuserforsa(){
        Query query=new Query();
        query.addCriteria(Criteria.where("sanalysis").is(true));
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z|A-Z]{2,6}$"));
        List<user> user=mongoTemplate.find(query,user.class);
        return user;
    }
}
