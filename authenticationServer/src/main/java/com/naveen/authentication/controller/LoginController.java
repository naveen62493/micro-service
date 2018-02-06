package com.naveen.authentication.controller;

import com.naveen.authentication.bean.LoginCredentials;
import com.naveen.authentication.bean.ResponseBean;
import com.naveen.authentication.bean.UserBean;
import com.naveen.authentication.exception.FailedToLoginException;
import com.naveen.authentication.security.JWTService;
import com.naveen.authentication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Naveen on 2/5/2018.
 */
@RestController
public class LoginController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(path = "/login",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseBean login(@RequestBody LoginCredentials loginCredentials, HttpServletResponse response){

        ResponseBean responseBean = new ResponseBean();
        UserBean user = loginService.findByUserName(loginCredentials.getUsername());

        if(null == user){
            throw new FailedToLoginException(loginCredentials.getUsername());
        }
        if(null != user && !passwordEncoder.matches(loginCredentials.getPassword(), user.getPassword())){
            responseBean.setFlag("failure");
            responseBean.setMessage("Exception occured");
            throw new FailedToLoginException(loginCredentials.getUsername());
        }

        String token = null;
        try{
            token = jwtService.tokenFor(loginCredentials.getUsername(),user.getRole());
            response.setHeader("token",token);
            loginService.updateToken(user,token,loginCredentials.getUserType());
        }catch (Exception e){
            responseBean.setFlag("failure");
            responseBean.setMessage("Exception occured");
            throw new RuntimeException(e);
        }

        responseBean.setRole(user.getRole());
        responseBean.setFlag("success");
        responseBean.setMessage(token);

        return responseBean;


    }
}
