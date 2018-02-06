package com.naveen.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Naveen on 2/5/2018.
 */
@SpringBootApplication
public class AuthenticationApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class,args);
    }
}
