package com.kristjan.webshop.controller.homework;

import com.kristjan.webshop.dto.Photo;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PhotoController {
    @GetMapping("photos")
    public Photo[] getPhotos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Photo[]> response =
                restTemplate.exchange("https://jsonplaceholder.typicode.com/photos", HttpMethod.GET, null, Photo[].class);
        return response.getBody();
    }
}
