package com.kristjan.webshop.security;

import com.kristjan.webshop.dto.security.AuthToken;
import com.kristjan.webshop.entity.Person;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    // HILJEM KINDLASTI appication.properties failist (ei tohi Githubi panna!!)
    private String securityKey = "c3JamEI9m0kf4NUaeb0KvrbXdTCMrDdYezn7zPmwSC0op2JaHESnuOHtcpQx1+hYyce6DbnHJ0CMoW0+vrIzf3VTUIPmJg==";

    public AuthToken getToken(Person person) {
        AuthToken authToken = new AuthToken();

        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60);
        authToken.setExpiration(expiration);

        String jwtToken = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey)), SignatureAlgorithm.HS512)
                .setAudience("Kristjan's webshop")
                .setExpiration(expiration)
                .setSubject(person.getId().toString())
                .setAudience(String.valueOf(person.isAdmin()))
                .compact();
        authToken.setToken(jwtToken);

        return authToken;
    }
}
