package com.kristjan.tirechangeworkshop.controller;

import com.kristjan.tirechangeworkshop.dto.Time;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Workshop {

    @GetMapping("workshop")
    public Time getNordpoolPrices() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Time> response = restTemplate.exchange("http://localhost:9004/api/v2/tire-change-times?amount=10&page=0&from=2006-01-02", HttpMethod.GET, null, Time.class);

        return response.getBody();
    }
}
