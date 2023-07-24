package com.kristjan.webshop.controller.homework;

import com.kristjan.webshop.dto.nordpool.Nordpool;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NordPoolController {

    @GetMapping("nordpool")
    public Nordpool getNordpoolPrices() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Nordpool> response = restTemplate.exchange("https://dashboard.elering.ee/api/nps/price", HttpMethod.GET, null, Nordpool.class);

        return response.getBody();
    }

    @GetMapping("nordpool/{country}")
    public String getParcelMachines(@PathVariable String country) {
        String countryCode = country.toUpperCase();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Nordpool> response = restTemplate.exchange("https://dashboard.elering.ee/api/nps/price", HttpMethod.GET, null, Nordpool.class);

        return null;
    }
}
