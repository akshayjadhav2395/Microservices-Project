package com.aj.hotel.service.service;

import com.aj.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelServiceI {

    public Hotel createHotel(Hotel hotel);
    public List<Hotel> getAllHotels();
    public Hotel updateHotel(Hotel hotel, String hotelId);
    public Hotel getSingleHotel(String hotelId);
    public void deleteHotel(String hotelId);
}
