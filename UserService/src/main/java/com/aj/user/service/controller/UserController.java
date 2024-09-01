package com.aj.user.service.controller;

import com.aj.user.service.entities.User;
import com.aj.user.service.service.UserServiceI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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

    int retryCount=1;

    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        logger.info("Get Single User Handler: UserController");

        logger.info("Retry count {}", retryCount);
        retryCount++;

        User user = this.userService.getSingleUser(userId);

        return new ResponseEntity<User> (user, HttpStatus.OK);
    }


    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception e)
    {
       // logger.info("Fallback method is executed because service is down..!!", e.getMessage());
        User user = User.builder()
                .userId("12345")
                .name("Dummy")
                .email("dummy@test.com")
                .about("this is dummy information because service is down..!!").build();

        return new ResponseEntity<User>(user, HttpStatus.OK);
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
