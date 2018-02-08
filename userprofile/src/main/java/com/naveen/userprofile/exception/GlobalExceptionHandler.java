package com.naveen.userprofile.exception;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created by Naveen on 2/8/2018.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @SuppressWarnings("unchecked")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(FailedToLoginException.class)
    public @ResponseBody String handleUserNotFoundException(HttpServletRequest request,Exception ex){
        JSONObject obj = new JSONObject();
        obj.put("url",request.getRequestURL().toString());
        obj.put("message",ex.getMessage());
        obj.put("status",HttpStatus.UNAUTHORIZED);

        return obj.toJSONString();
    }

    @SuppressWarnings("unchecked")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidTokenException.class)
    public @ResponseBody String handleTokenNotFoundException(HttpServletRequest request,Exception ex){
        JSONObject obj = new JSONObject();
        obj.put("url", request.getRequestURL().toString());
        obj.put("message", ex.getMessage());
        obj.put("status", UNAUTHORIZED);


        return obj.toJSONString();
    }
}
