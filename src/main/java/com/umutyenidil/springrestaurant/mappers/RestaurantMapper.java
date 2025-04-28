package com.umutyenidil.springrestaurant.mappers;

import com.umutyenidil.springrestaurant.domain.dtos.GeoPointDto;
import com.umutyenidil.springrestaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.umutyenidil.springrestaurant.domain.dtos.RestaurantDto;
import com.umutyenidil.springrestaurant.domain.dtos.RestaurantSummaryDto;
import com.umutyenidil.springrestaurant.domain.entities.Restaurant;
import com.umutyenidil.springrestaurant.domain.entities.Review;
import com.umutyenidil.springrestaurant.domain.requests.RestaurantCreateUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    RestaurantDto toRestaurantDto(Restaurant restaurant);

    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);

    @Mapping(source = "reviews", target = "totalReviews", qualifiedByName = "populateTotalReviews")
    RestaurantSummaryDto toSummaryDto(Restaurant restaurant);

    @Named("populateTotalReviews")
    default Integer populateTotalReviews(List<Review> reviews) {
        return reviews.size();
    }
}
