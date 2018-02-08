package com.naveen.userprofile.security;

import com.naveen.userprofile.bean.JWTAuthToken;
import com.naveen.userprofile.bean.JWTAuthenticationProfile;
import com.naveen.userprofile.exception.JWTAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by Naveen on 2/8/2018.
 */
@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private final JWTService jwtService;

    public JWTAuthenticationProvider(){
        this.jwtService=null;
    }
    @Autowired
    public JWTAuthenticationProvider(JWTService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try{
            Jws<Claims> claimsJws = jwtService.verify((String)authentication.getCredentials());
            String currentUser = claimsJws.getBody().getSubject();
            String role = claimsJws.getBody().get("role",String.class);

            JWTAuthenticationProfile profile = new JWTAuthenticationProfile(currentUser);
            profile.setUserRole(role);

            return profile;
        }catch (Exception e){
            throw new JWTAuthenticationException("Failed To Verify Token: ",e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthToken.class.equals(authentication);
    }

}
