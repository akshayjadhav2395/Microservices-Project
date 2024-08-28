package com.aj.rating.service.controller;

import com.aj.rating.service.entities.Rating;
import com.aj.rating.service.service.RatingServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingServiceI ratingService;

    @PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
    {
        Rating rating1 = this.ratingService.createRating(rating);

        return new ResponseEntity<Rating>(rating1, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAllRatings()
    {
        List<Rating> ratingList = this.ratingService.getAllRatings();

        return new ResponseEntity<List<Rating> >(ratingList, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId)
    {
        List<Rating> ratingList = this.ratingService.getRatingByUserId(userId);

        return new ResponseEntity<List<Rating>> (ratingList, HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId)
    {
        List<Rating> ratingList = this.ratingService.getRatingByHotelId( hotelId);

        return new ResponseEntity<List<Rating>> (ratingList, HttpStatus.OK);
    }

    @DeleteMapping ("/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable String ratingId)
    {
        this.ratingService.deleteRating(ratingId);

        return new ResponseEntity<Void> (HttpStatus.OK);
    }

}
