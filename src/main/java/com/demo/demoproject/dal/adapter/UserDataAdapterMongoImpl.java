package com.demo.demoproject.dal.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.demo.demoproject.dal.model.UserModel;
import com.demo.demoproject.dal.repository.userMongoRepository;
import com.demo.demoproject.domain.User;
import com.demo.demoproject.domain.UserDataAdapter;

@Component
public class UserDataAdapterMongoImpl implements UserDataAdapter{

    private final userMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserDataAdapterMongoImpl(userMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User AddUser(User user) {
       UserModel userModel = new UserModel();
       userModel.setDescription(user.getDescription());
       userModel.setEmail(user.getEmail());
       userModel.setPassword(user.getPassword());
       userModel.setType(user.getType());
       userModel.setUsername(user.getUsername());
       userModel = repository.save(userModel);
       user.setUserId(userModel.getUserId());
       return user;
    }

    @Override
    public User UpdateUser(User user) {
       UserModel userModel = mongoTemplate.findAndModify(Query.query(Criteria.where("userId").is(user.getUserId())),
       new Update().set("username", user.getUsername()).set("password", user.getPassword()).set("type", user.getType()).set("email", user.getEmail()).set("description", user.getDescription()),UserModel.class);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<UserModel> userModels = repository.findAll();
        List<User> users = new ArrayList<>();

        for(UserModel userModel: userModels){
            User user = new User();
            user.setUserId(userModel.getUserId());
            user.setDescription(userModel.getDescription());
            user.setEmail(userModel.getEmail());
            user.setPassword(userModel.getPassword());
            user.setType(userModel.getType());
            user.setUsername(userModel.getUsername());
            users.add(user);
        }
        return users;
    }

    @Override
    public void DeleteUser(String userId) {
       
        repository.deleteUserModelByUserId(userId);
        
    }

    @Override
    public Optional<UserModel> getSingleUser(String userId) {
        
        return repository.findUserModelByEmail(userId);
    }
    
}
