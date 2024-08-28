package com.aj.hotel.service.service.impl;

import com.aj.hotel.service.entities.Hotel;
import com.aj.hotel.service.exception.ResourceNotFoundException;
import com.aj.hotel.service.repository.HotelRepository;
import com.aj.hotel.service.service.HotelServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelServiceI {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {

        String randomHotelId = UUID.randomUUID().toString();

        hotel.setHotelId(randomHotelId);

        Hotel savedHotel = this.hotelRepository.save(hotel);
        return savedHotel;
    }

    @Override
    public List<Hotel> getAllHotels() {

        List<Hotel> hotels = this.hotelRepository.findAll();

        return hotels;
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String hotelId) {

        Hotel hotel1 = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with hotelId: " + hotelId));

        hotel1.setHotelName(hotel.getHotelName());
        hotel1.setAbout(hotel.getAbout());
        hotel1.setLocation(hotel.getLocation());

        Hotel updatedHotel = this.hotelRepository.save(hotel1);

        return updatedHotel;
    }

    @Override
    public Hotel getSingleHotel(String hotelId) {

        Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with hotelId: " + hotelId + "not found..!"));

        return hotel;
    }

    @Override
    public void deleteHotel(String hotelId) {

        Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with hotelId: " + hotelId + "not found..!"));

        this.hotelRepository.delete(hotel);
    }

}
