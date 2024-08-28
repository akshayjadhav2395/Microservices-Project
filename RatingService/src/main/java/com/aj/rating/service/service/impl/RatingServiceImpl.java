package com.aj.rating.service.service.impl;

import com.aj.rating.service.entities.Rating;
import com.aj.rating.service.exception.ResourceNotFoundException;
import com.aj.rating.service.repository.RatingRepository;
import com.aj.rating.service.service.RatingServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingServiceI {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Rating createRating(Rating rating) {

        String randomRatingId = UUID.randomUUID().toString();

        rating.setRatingId(randomRatingId);

        Rating savedRating = this.ratingRepository.save(rating);
        return savedRating;
    }

    @Override
    public List<Rating> getAllRatings() {

        List<Rating> ratings = this.ratingRepository.findAll();

        return ratings;
    }

//    @Override
//    public Rating updateRating (Rating rating, String ratingId) {
//
//        Rating rating1 = this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with ratingId: " + ratingId));
//
//        rating1.setRating(rating.getRating());
//        rating1.setFeedback(rating.getFeedback());
//
//
//        Rating updatedRating = this.ratingRepository.save(rating1);
//
//        return updatedRating;
//    }

    @Override
    public List<Rating> getRatingByUserId (String userId) {

        List<Rating> byUserId = this.ratingRepository.findByUserId(userId);

        return byUserId;
    }

    @Override
    public List<Rating> getRatingByHotelId (String hotelId) {

        List<Rating> byHotelId = this.ratingRepository.findByHotelId(hotelId);

        return byHotelId;
    }

    @Override
    public void deleteRating(String ratingId) {

        Rating rating = this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with ratingId: " + ratingId + "not found..!"));

        this.ratingRepository.delete(rating);
    }
}

