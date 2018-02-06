package com.naveen.authentication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Naveen on 2/5/2018.
 */
@Component
public class JWTService {

    private static final String ISSUER ="naveen.com";
    private SecretKeyProvider secretKeyProvider;

    public JWTService(){
        this(null);
    }

    @Autowired
    public JWTService(SecretKeyProvider secretKeyProvider){
        this.secretKeyProvider = secretKeyProvider;
    }

    public String tokenFor(String user,String role)throws IOException,URISyntaxException{
        byte[] secretKey = secretKeyProvider.getKey();

        String uuid = UUID.randomUUID().toString().replace("-","");
        Date expiration = new Date();
        Calendar c  = Calendar.getInstance();
        c.setTime(expiration);
        c.add(Calendar.MINUTE,5);
        expiration = c.getTime();
        return Jwts.builder()
                .setId(uuid)
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .claim("role",role)
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
    }

    public Jws<Claims> verify(String token) throws IOException,URISyntaxException{
        byte[] secretKey = secretKeyProvider.getKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }

}
