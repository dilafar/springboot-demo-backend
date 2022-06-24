package com.demo.demoproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demoproject.domain.User;
import com.demo.demoproject.domain.UserDataAdapter;

@Service
public class UserApi {

    private final UserDataAdapter userDataAdapter;

    @Autowired
    public UserApi(UserDataAdapter userDataAdapter) {
        this.userDataAdapter = userDataAdapter;
    }

    public List<User> getAllUser(){
        return userDataAdapter.getAll();
    }

    public User SaveUser(User user){
        return userDataAdapter.AddUser(user);
    }

    public User UpdateUser(User user){
        return userDataAdapter.UpdateUser(user);
    }

    public void Deleteuser(String userId){
         userDataAdapter.DeleteUser(userId);
    }
}
