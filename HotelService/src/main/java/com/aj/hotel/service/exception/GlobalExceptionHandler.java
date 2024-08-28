package com.aj.hotel.service.exception;

import com.aj.hotel.service.payload.ApiResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception)
//    {
//        String message = exception.getMessage();
//
//        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
//
//        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> ResourceNotFoundExceptionHandler(ResourceNotFoundException exception)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("message", exception.getMessage());
        map.put("success", false);
        map.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
    }
}
