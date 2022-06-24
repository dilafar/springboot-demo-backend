package com.demo.demoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demoproject.domain.User;
import com.demo.demoproject.dto.userDto;
import com.demo.demoproject.service.UserApi;

@RestController
@RequestMapping("/user")
public class UserEndPoint {

    private final UserApi userApi;

    @Autowired
    public UserEndPoint(UserApi userApi) {
        this.userApi = userApi;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userApi.getAllUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User AddUser(@RequestBody userDto userDto){
        User user = new User();
        user.setDescription(userDto.getDescription());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setType(userDto.getType());
        user.setUsername(userDto.getUsername());

        return userApi.SaveUser(user);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public User EditUser(@PathVariable String userId , @RequestBody userDto userDto){
        User user = new User();
        user.setUserId(userId);
        user.setDescription(userDto.getDescription());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setType(userDto.getType());
        user.setUsername(userDto.getUsername());


        return userApi.UpdateUser(user);

    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId){
            userApi.Deleteuser(userId);
    }
    
}
