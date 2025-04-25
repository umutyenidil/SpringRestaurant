package com.umutyenidil.springrestaurant.services.impl;

import com.umutyenidil.springrestaurant.domain.GeoLocation;
import com.umutyenidil.springrestaurant.domain.entities.Address;
import com.umutyenidil.springrestaurant.services.GeoLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class RandomTurkeyGeoLocationService implements GeoLocationService {

    private static final float MIN_LATITUDE = 35.81f;
    private static final float MAX_LATITUDE = 42.11f;
    private static final float MIN_LONGITUDE = 25.66f;
    private static final float MAX_LONGITUDE = 44.82f;


    @Override
    public GeoLocation geoLocate(Address address) {
        Random random = new Random();
        double latitude = MIN_LATITUDE + random.nextDouble() * (MAX_LATITUDE - MIN_LATITUDE);
        double longitude = MIN_LONGITUDE + random.nextDouble() * (MAX_LONGITUDE - MIN_LONGITUDE);

        return GeoLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
