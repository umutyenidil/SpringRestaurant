package com.umutyenidil.springrestaurant.controllers;

import com.umutyenidil.springrestaurant.domain.dtos.PhotoDto;
import com.umutyenidil.springrestaurant.domain.entities.Photo;
import com.umutyenidil.springrestaurant.mappers.PhotoMapper;
import com.umutyenidil.springrestaurant.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/photos")
public class PhotoController {
    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping
    public PhotoDto uploadPhoto(
            @RequestParam("file") MultipartFile file
    ) {
        Photo savedPhoto = photoService.uploadPhoto(file);

        return photoMapper.toDto(savedPhoto);
    }
}
