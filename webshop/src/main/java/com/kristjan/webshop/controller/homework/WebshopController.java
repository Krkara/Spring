package com.kristjan.webshop.controller.homework;

import com.kristjan.webshop.dto.Webshop;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebshopController {
    @GetMapping("phones")
    public Webshop getProducts() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<com.kristjan.webshop.dto.Webshop> response = restTemplate.exchange("https://dummyjson.com/products", HttpMethod.GET, null, Webshop.class);

        return response.getBody();
    }
}
