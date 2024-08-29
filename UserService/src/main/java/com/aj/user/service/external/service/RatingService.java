package com.aj.user.service.external.service;

import com.aj.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping("/ratings/users/{userId}")
    public List<Rating> getRatingByUserId(@PathVariable String userId);

}
