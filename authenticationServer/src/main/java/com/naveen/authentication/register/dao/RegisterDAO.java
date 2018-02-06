package com.naveen.authentication.register.dao;

import com.naveen.authentication.bean.UserBean;

/**
 * Created by Naveen on 2/5/2018.
 */
public interface RegisterDAO {

    public boolean registerUser(UserBean user);
    public boolean checkUser(String user);
}
