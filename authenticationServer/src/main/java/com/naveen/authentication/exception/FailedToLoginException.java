package com.naveen.authentication.exception;

import static java.lang.String.format;

/**
 * Created by Naveen on 2/5/2018.
 */
@SuppressWarnings("serial")
public class FailedToLoginException extends RuntimeException {

    public FailedToLoginException(String username){
        super(format("failed to login with username %s",username));
    }
}
