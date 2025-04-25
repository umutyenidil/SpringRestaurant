package com.umutyenidil.springrestaurant.services;

import com.umutyenidil.springrestaurant.domain.GeoLocation;
import com.umutyenidil.springrestaurant.domain.entities.Address;

public interface GeoLocationService {
    GeoLocation geoLocate(Address address);
}
