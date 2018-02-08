package com.naveen.userprofile.exception;

import static java.lang.String.format;

/**
 * Created by Naveen on 2/8/2018.
 */
@SuppressWarnings("serial")
public class FailedToLoginException extends RuntimeException {
    public FailedToLoginException(String username) {
        super(format("Failed to login with username %s", username));
    }
}
