package com.aj.user.service.service.impl;

import com.aj.user.service.entities.Hotel;
import com.aj.user.service.entities.Rating;
import com.aj.user.service.entities.User;
import com.aj.user.service.exceptions.ResourceNotFoundException;
import com.aj.user.service.external.service.HotelService;
import com.aj.user.service.repository.UserRepository;
import com.aj.user.service.service.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {

        String randomUserId = UUID.randomUUID().toString();

        user.setUserId(randomUserId);

        User savedUser = this.userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = this.userRepository.findAll();

        List<User> userList = users.stream().map(user -> {

            //fetching rating of user
            Rating[] ratingOfUser = this.restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

            List<Rating> ratingList = ratings.stream().map((rating -> {

                //now fetching hotel details
                //  http://localhost:9092/hotels/b590c9ae-5c43-47ff-8c33-a7882b3b6638
                //Hotel hotel = this.restTemplate.getForObject("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = this.hotelService.getHotel(rating.getHotelId());

                rating.setHotel(hotel);

                return rating;
            })).collect(Collectors.toList());

            //setting ratings to user so we'll get users with all ratings with hotelDetails
            user.setRatings(ratingList);

            return user;
        }).collect(Collectors.toList());


        return userList;
    }

    @Override
    public User updateUser(User user, String userId) {

        User user1 = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));

        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAbout(user.getAbout());

        User updatedUser = this.userRepository.save(user1);

        return updatedUser;
    }

    @Override
    public User getSingleUser(String userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with userId: " + userId + "not found..!"));

//        http://localhost:9094/ratings/users/a5aa91d3-4643-44cc-95f7-7780dbc84631
//         get ratings of particular user

       Rating[] ratingOfUser = this.restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+userId, Rating[].class);
        logger.info("{}", ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map((rating) -> {

            //now fetching hotel details
            //  http://localhost:9092/hotels/b590c9ae-5c43-47ff-8c33-a7882b3b6638
            //Hotel hotel = this.restTemplate.getForObject("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            Hotel hotel = this.hotelService.getHotel(rating.getHotelId());

            //set hotel to rating
            rating.setHotel(hotel);

            //return the rating
            return rating;

        }).collect(Collectors.toList());


        user.setRatings(ratingList);

        return user;
    }

    @Override
    public void deleteUser(String userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with userId: " + userId + "not found..!"));

        this.userRepository.delete(user);
    }
}
