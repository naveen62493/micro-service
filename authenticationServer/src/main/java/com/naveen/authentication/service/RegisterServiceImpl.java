package com.naveen.authentication.service;

import com.naveen.authentication.bean.RegisterBean;
import com.naveen.authentication.bean.ResponseBean;
import com.naveen.authentication.bean.UserBean;
import com.naveen.authentication.bean.UserToken;
import com.naveen.authentication.constants.CommonConstants;
import com.naveen.authentication.register.dao.RegisterDAO;
import com.naveen.authentication.register.validation.UserValidation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Naveen on 2/5/2018.
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    public RegisterDAO registerDAO;

    @Autowired
    public UserValidation userValidation;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    public ResponseBean registerUser(RegisterBean register) {

        ResponseBean reponseBean = new ResponseBean();

        String validation = userValidation.validateUser(register);
        if(StringUtils.isNotBlank(validation)){
            reponseBean.setFlag("failure");
            reponseBean.setMessage(validation);
            return reponseBean;
        }

        if(registerDAO.checkUser(register.getUsername())){
            reponseBean.setFlag("failure");
            reponseBean.setMessage("User already exist, please try another name");
            return reponseBean;
        }

        UserBean userBean = new UserBean();
        userBean.setUsername(register.getUsername());
        userBean.setPassword(passwordEncoder.encode(register.getPassword()));
        userBean.setRole(CommonConstants.ROLE_USER);

        UserToken tokenWeb = new UserToken();
        tokenWeb.setUserType(CommonConstants.WEB_USER);
        tokenWeb.setUser(userBean);

        UserToken tokenAn = new UserToken();
        tokenAn.setUserType(CommonConstants.ANDRIOD_USER);
        tokenAn.setUser(userBean);

        userBean.getUserToken().add(tokenWeb);
        userBean.getUserToken().add(tokenAn);

        boolean creationFlag = registerDAO.registerUser(userBean);
        if(creationFlag){
            reponseBean.setFlag("success");
            reponseBean.setMessage("User Registered Successfully, please login to continue");
            return reponseBean;
        } else {
            reponseBean.setFlag("failure");
            reponseBean.setMessage("User Registered Failed");
            return reponseBean;
        }
    }

}
