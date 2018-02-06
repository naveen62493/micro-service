package com.naveen.authentication.exception;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created by Naveen on 2/5/2018.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @SuppressWarnings("unchecked")
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(FailedToLoginException.class)
    public @ResponseBody String handleUserNotFoundException(HttpServletRequest request,Exception ex){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url",request.getRequestURL().toString());
        jsonObject.put("message",ex.getMessage());
        jsonObject.put("status", UNAUTHORIZED);

        return jsonObject.toJSONString();
    }

    @SuppressWarnings("unchecked")
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(InvalidTokenException.class)
    public @ResponseBody String handleUserNotFoundException1(HttpServletRequest request, Exception ex){

        JSONObject obj = new JSONObject();
        obj.put("url", request.getRequestURL().toString());
        obj.put("message", ex.getMessage());
        obj.put("status", UNAUTHORIZED);


        return obj.toJSONString();
    }


}
