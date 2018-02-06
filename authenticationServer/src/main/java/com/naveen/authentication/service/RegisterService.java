package com.naveen.authentication.service;

import com.naveen.authentication.bean.RegisterBean;
import com.naveen.authentication.bean.ResponseBean;

/**
 * Created by Naveen on 2/5/2018.
 */

public interface RegisterService {

    ResponseBean registerUser(RegisterBean register);
}
