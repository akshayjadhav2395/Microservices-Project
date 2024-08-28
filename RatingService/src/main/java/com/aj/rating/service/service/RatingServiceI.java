package com.aj.rating.service.service;

import com.aj.rating.service.entities.Rating;

import java.util.List;

public interface RatingServiceI {

    public Rating createRating(Rating rating);
    public List<Rating> getAllRatings();
//    public Rating updateRating(Rating rating, String ratingId);
    public List<Rating> getRatingByUserId(String userId);
    public List<Rating> getRatingByHotelId(String hotelId);
    public void deleteRating(String ratingId);
}
