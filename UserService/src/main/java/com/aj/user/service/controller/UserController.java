package com.aj.user.service.controller;

import com.aj.user.service.entities.User;
import com.aj.user.service.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceI userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1 = this.userService.createUser(user);

        return new ResponseEntity<User>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> userList = this.userService.getAllUsers();

        return new ResponseEntity<List<User> >(userList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        User user = this.userService.getSingleUser(userId);

        return new ResponseEntity<User> (user, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId)
    {
        User user1 = this.userService.updateUser(user, userId);

        return new ResponseEntity<User> (user1, HttpStatus.OK);
    }

    @DeleteMapping ("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId)
    {
         this.userService.deleteUser(userId);

        return new ResponseEntity<Void> (HttpStatus.OK);
    }


}
