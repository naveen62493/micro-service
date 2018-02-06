package com.naveen.authentication.service;

import com.naveen.authentication.bean.UserBean;
import com.naveen.authentication.bean.UserToken;
import com.naveen.authentication.dao.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Naveen on 2/5/2018.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    public LoginDAO loginDAO;

    @Override
    public UserBean findByUserName(String userName) {
        return loginDAO.findByUserName(userName);
    }

    @Override
    public boolean updateToken(UserBean userBean, String token, String userType) {
        Set<UserToken> userTypes = userBean.getUserToken();
        for(UserToken type: userTypes){
            if(type.getUserType().equalsIgnoreCase(userType)){
                type.setToken(token);
            }
        }
        return loginDAO.updateToken(userBean);
    }
}
