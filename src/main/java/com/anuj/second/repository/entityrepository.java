package com.anuj.second.repository;

import com.anuj.second.entity.entry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface entityrepository extends MongoRepository<entry,String> {
}
