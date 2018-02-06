package com.naveen.authentication.service;

import com.naveen.authentication.bean.UserBean;

/**
 * Created by Naveen on 2/5/2018.
 */
public interface LoginService {

    UserBean findByUserName(String userName);

    boolean updateToken(UserBean userBean,String token, String userType);
}
