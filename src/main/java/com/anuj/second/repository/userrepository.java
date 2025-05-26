package com.anuj.second.repository;

import com.anuj.second.entity.user;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userrepository extends MongoRepository<user,String> {
    user findByusername(String username);
    user deleteByusername(String username);
}
