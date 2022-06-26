package com.demo.demoproject.domain;

import java.util.List;
import java.util.Optional;

import com.demo.demoproject.dal.model.UserModel;

public interface UserDataAdapter {
    
    User AddUser(User user);
    User UpdateUser(User user);
    List<User> getAll();
    void DeleteUser(String userId);
    Optional<UserModel> getSingleUser(String userId);
    
}
