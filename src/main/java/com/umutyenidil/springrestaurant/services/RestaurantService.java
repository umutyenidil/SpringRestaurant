package com.umutyenidil.springrestaurant.services;

import com.umutyenidil.springrestaurant.domain.entities.Restaurant;
import com.umutyenidil.springrestaurant.domain.requests.RestaurantCreateUpdateRequest;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantCreateUpdateRequest request);
}
