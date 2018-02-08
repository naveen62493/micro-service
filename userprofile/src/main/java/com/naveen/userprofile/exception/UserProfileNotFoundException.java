package com.naveen.userprofile.exception;

/**
 * Created by Naveen on 2/8/2018.
 */
public class UserProfileNotFoundException extends RuntimeException {

    public UserProfileNotFoundException(String id){
        super(String.format("No User Profile found with id: <%s> ",id));
    }
}
