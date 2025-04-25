package com.umutyenidil.springrestaurant.controllers;

import com.umutyenidil.springrestaurant.domain.dtos.PhotoDto;
import com.umutyenidil.springrestaurant.domain.entities.Photo;
import com.umutyenidil.springrestaurant.mappers.PhotoMapper;
import com.umutyenidil.springrestaurant.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(path = "/{id:.+}")
    public ResponseEntity<Resource> getPhoto(
            @PathVariable("id") String id
    ) {
        return photoService.getPhotoAsResource(id)
                .map(photo -> ResponseEntity.ok()
                        .contentType(
                                MediaTypeFactory.getMediaType(photo)
                                        .orElse(MediaType.APPLICATION_OCTET_STREAM)
                        )
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                        .body(photo)
                )
                .orElse(ResponseEntity.notFound().build());
    }
}
