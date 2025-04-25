package com.umutyenidil.springrestaurant.controllers;

import com.umutyenidil.springrestaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.umutyenidil.springrestaurant.domain.dtos.RestaurantDto;
import com.umutyenidil.springrestaurant.domain.entities.Restaurant;
import com.umutyenidil.springrestaurant.domain.requests.RestaurantCreateUpdateRequest;
import com.umutyenidil.springrestaurant.mappers.RestaurantMapper;
import com.umutyenidil.springrestaurant.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(
            @Valid @RequestBody RestaurantCreateUpdateRequestDto request
    ) {
        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest = restaurantMapper.toRestaurantCreateUpdateRequest(request);

        Restaurant restaurant = restaurantService.createRestaurant(restaurantCreateUpdateRequest);

        RestaurantDto restaurantDto = restaurantMapper.toRestaurantDto(restaurant);

        return new ResponseEntity<>(restaurantDto, HttpStatus.CREATED);
    }
}
