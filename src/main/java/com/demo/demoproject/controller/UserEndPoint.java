package com.demo.demoproject.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demoproject.dal.model.UserModel;
import com.demo.demoproject.domain.User;
import com.demo.demoproject.dto.userDto;
import com.demo.demoproject.service.UserApi;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserEndPoint {

    private final UserApi userApi;

    @Autowired
    public UserEndPoint(UserApi userApi) {
        this.userApi = userApi;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try{
            List<User> users = userApi.getAllUser();
            if(users.size()>0){
                return new ResponseEntity<List<User>>(users, HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Users Not Available", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }

    @PostMapping
   // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> AddUser(@RequestBody userDto userDto){
        Optional<UserModel> model = userApi.getSingle(userDto.getEmail());
        if(!model.isPresent()){
            
            User user = new User();
            user.setDescription(userDto.getDescription());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setType(userDto.getType());
            user.setUsername(userDto.getUsername());

            User user2 = userApi.SaveUser(user);

            return new ResponseEntity<User>(user2 , HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("User Already Exists" , HttpStatus.NOT_FOUND);
        }
        
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
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        try{
            userApi.Deleteuser(userId);
            return new ResponseEntity<String>("User Successfully Deleted", HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
            
    }
    
}
