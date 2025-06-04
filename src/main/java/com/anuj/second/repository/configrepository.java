package com.anuj.second.repository;

import com.anuj.second.entity.configentity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface configrepository extends MongoRepository<configentity,String> {

}
