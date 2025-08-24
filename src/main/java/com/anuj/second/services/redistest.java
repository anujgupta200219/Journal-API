package com.anuj.second.services;

import com.anuj.second.entity.user;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class redistest {

    @Autowired
    private RedisTemplate redisTemplate;

    void sendEmail(){
        redisTemplate.opsForValue().set("email","guptaanuj1905@gmail.com");
    }
}
