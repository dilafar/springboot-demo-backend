package com.demo.demoproject.dal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.demoproject.dal.model.UserModel;

@Repository
public interface userMongoRepository extends MongoRepository<UserModel , String>{
    
    void deleteUserModelByUserId(String userid);
}
