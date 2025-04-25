package com.umutyenidil.springrestaurant.mappers;

import com.umutyenidil.springrestaurant.domain.dtos.PhotoDto;
import com.umutyenidil.springrestaurant.domain.entities.Photo;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);

    Photo toPhoto(PhotoDto photoDto);
}
