package com.naveen.userprofile.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Naveen on 2/8/2018.
 */
public class JWTAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 1L;
    public JWTAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}
