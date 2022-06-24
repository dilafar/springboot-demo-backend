package com.demo.demoproject.domain;

import java.util.List;

public interface UserDataAdapter {
    
    User AddUser(User user);
    User UpdateUser(User user);
    List<User> getAll();
    void DeleteUser(String userId);
    
}
