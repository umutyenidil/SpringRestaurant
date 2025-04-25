package com.umutyenidil.springrestaurant.services.impl;

import com.umutyenidil.springrestaurant.domain.GeoLocation;
import com.umutyenidil.springrestaurant.domain.entities.Address;
import com.umutyenidil.springrestaurant.domain.entities.Photo;
import com.umutyenidil.springrestaurant.domain.entities.Restaurant;
import com.umutyenidil.springrestaurant.domain.requests.RestaurantCreateUpdateRequest;
import com.umutyenidil.springrestaurant.repositories.RestaurantRepository;
import com.umutyenidil.springrestaurant.services.GeoLocationService;
import com.umutyenidil.springrestaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final GeoLocationService geoLocationService;
    private final ClientHttpRequestFactorySettings clientHttpRequestFactorySettings;

    @Override
    public Restaurant createRestaurant(RestaurantCreateUpdateRequest request) {
        Address address = request.getAddress();
        GeoLocation geoLocation = geoLocationService.geoLocate(address);
        GeoPoint geoPoint = new GeoPoint(geoLocation.getLatitude(), geoLocation.getLongitude());

        List<String> photoIds = request.getPhotoIds();
        List<Photo> photos = photoIds.stream().map(photoUrl -> Photo.builder()
                        .url(photoUrl)
                        .uploadDate(LocalDateTime.now())
                        .build())
                .toList();

        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .cuisineType(request.getCuisineType())
                .contactInformation(request.getContactInformation())
                .address(address)
                .geoLocation(geoPoint)
                .operatingHours(request.getOperatingHours())
                .averageRating(0f)
                .photos(photos)
                .build();

        return restaurantRepository.save(restaurant);
    }
}
