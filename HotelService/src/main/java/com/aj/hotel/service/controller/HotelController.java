package com.aj.hotel.service.controller;

import com.aj.hotel.service.entities.Hotel;
import com.aj.hotel.service.service.HotelServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServiceI hotelService;

    @PostMapping("/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        Hotel hotel1 = this.hotelService.createHotel(hotel);

        return new ResponseEntity<Hotel>(hotel1, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        List<Hotel> hotelList = this.hotelService.getAllHotels();

        return new ResponseEntity<List<Hotel> >(hotelList, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId)
    {
        Hotel hotel = this.hotelService.getSingleHotel(hotelId);

        return new ResponseEntity<Hotel> (hotel, HttpStatus.OK);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId)
    {
        Hotel hotel1 = this.hotelService.updateHotel(hotel, hotelId);

        return new ResponseEntity<Hotel> (hotel1, HttpStatus.OK);
    }

    @DeleteMapping ("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String hotelId)
    {
        this.hotelService.deleteHotel(hotelId);

        return new ResponseEntity<Void> (HttpStatus.OK);
    }
}
