package com.aj.hotel.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/")
    public ResponseEntity<List<String>> getStaffData()
    {
        List<String> staffList = List.of("Ram, Akshay, Shyam, Balvant");

        return new ResponseEntity<List<String>>( staffList, HttpStatus.OK);
    }
}
