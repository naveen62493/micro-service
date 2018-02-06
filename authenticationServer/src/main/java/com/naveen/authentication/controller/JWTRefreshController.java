package com.naveen.authentication.controller;

import com.naveen.authentication.bean.LoginCredentials;
import com.naveen.authentication.bean.ResponseBean;
import com.naveen.authentication.bean.UserBean;
import com.naveen.authentication.bean.UserToken;
import com.naveen.authentication.exception.FailedToLoginException;
import com.naveen.authentication.exception.InvalidTokenException;
import com.naveen.authentication.security.JWTService;
import com.naveen.authentication.service.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Naveen on 2/5/2018.
 */
@RestController
public class JWTRefreshController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private LoginService loginService;


    @RequestMapping(path = "/refresh",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseBean login(@RequestBody LoginCredentials loginCredentials, HttpServletResponse response){
        ResponseBean responseBean = new ResponseBean();

        String newToken = null;
        if(StringUtils.isNotBlank(loginCredentials.getToken()) && StringUtils.isNotBlank(loginCredentials.getUsername())){

            UserBean user = loginService.findByUserName(loginCredentials.getUsername());

            if(null == user){
                responseBean.setFlag("failure");
                responseBean.setMessage("User not found");
                throw new FailedToLoginException(loginCredentials.getUsername());
            }

            Set<UserToken> tokenSet = user.getUserToken();
            Iterator<UserToken> it = tokenSet.iterator();

            while (it.hasNext()){
                UserToken userToken = it.next();
                if(userToken.getUserType().equals(loginCredentials.getUserType())){
                    if(!userToken.getToken().equals(loginCredentials.getToken())){
                        responseBean.setFlag("Failure");
                        responseBean.setMessage("Invalid Token");
                        throw new InvalidTokenException(loginCredentials.getUsername());
                    }
                }
            }

            try{
                Jws<Claims> claimsJws = jwtService.verify(loginCredentials.getToken());
                String username = claimsJws.getBody().getSubject();

                if(null != username && !username.equals(loginCredentials.getUsername())){
                    responseBean.setFlag("failure");
                    responseBean.setMessage("Username is not correct");
                    throw new FailedToLoginException(loginCredentials.getUsername());
                }
            }catch (Exception e){
                try{
                    newToken = jwtService.tokenFor(loginCredentials.getUsername(),user.getRole());
                    loginService.updateToken(user,newToken,loginCredentials.getUserType());
                }catch (Exception e1){
                    throw new FailedToLoginException(loginCredentials.getUsername());
                }
                responseBean.setRole(user.getRole());
                responseBean.setFlag("Success");
                responseBean.setMessage(newToken);
                response.setHeader("token",newToken);
            }

            }else{
            responseBean.setFlag("Failure");
            responseBean.setMessage("Parameters are not valid or not found");
            throw new FailedToLoginException(loginCredentials.getUsername());
        }
        return responseBean;
    }
}
