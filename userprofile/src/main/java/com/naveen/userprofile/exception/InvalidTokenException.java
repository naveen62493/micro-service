package com.naveen.userprofile.exception;

import static java.lang.String.format;

/**
 * Created by Naveen on 2/8/2018.
 */
@SuppressWarnings("serial")
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String username) {
        super(format("Token Invalid for username %s", username));
    }
}
