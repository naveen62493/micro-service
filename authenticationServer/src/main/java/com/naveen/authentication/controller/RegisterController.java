package com.naveen.authentication.controller;

import com.naveen.authentication.bean.RegisterBean;
import com.naveen.authentication.bean.ResponseBean;
import com.naveen.authentication.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Naveen on 2/5/2018.
 */
@RestController
public class RegisterController {
    @Autowired
    public RegisterService registerService;

    @RequestMapping(path = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseBean register(@RequestBody RegisterBean registerBean,
                                 HttpServletResponse response) {
        return registerService.registerUser(registerBean);
    }
}
