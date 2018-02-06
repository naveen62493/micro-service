package com.naveen.authentication.bean;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Naveen on 2/5/2018.
 */
@SuppressWarnings("serial")
public class JWTAuthenticationProfile implements Authentication {

    private final String minimalProfile;

    private String userRole;

    public JWTAuthenticationProfile(String minimalProfile) {
        this.minimalProfile = minimalProfile;
    }

    public void setUserRole(String userRole){
        this.userRole=userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.userRole));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return minimalProfile;
    }
}
