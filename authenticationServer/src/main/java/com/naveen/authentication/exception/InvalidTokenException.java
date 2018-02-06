package com.naveen.authentication.exception;

import static java.lang.String.format;

/**
 * Created by Naveen on 2/5/2018.
 */
@SuppressWarnings("serial")
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String username) {
        super(format("Token Invalid for username %s", username));
    }
}
