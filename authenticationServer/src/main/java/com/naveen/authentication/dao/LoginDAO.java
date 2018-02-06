package com.naveen.authentication.dao;

import com.naveen.authentication.bean.UserBean;

/**
 * Created by Naveen on 2/5/2018.
 */
public interface LoginDAO {

    UserBean findByUserName(String username);

    boolean updateToken(UserBean userBean);
}
