package com.naveen.userprofile.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Naveen on 2/8/2018.
 */
@Component
public class JWTService {

    private SecretKeyProvider secretKeyProvider;

    public JWTService(){
        this.secretKeyProvider=null;
    }
    @Autowired
    public JWTService(SecretKeyProvider secretKeyProvider){this.secretKeyProvider=secretKeyProvider;}

    public Jws<Claims> verify(String token) throws IOException,URISyntaxException{
        byte[] secretKey = secretKeyProvider.getKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }
}
