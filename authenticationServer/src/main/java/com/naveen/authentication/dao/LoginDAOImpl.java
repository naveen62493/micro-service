package com.naveen.authentication.dao;

import com.naveen.authentication.bean.UserBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Naveen on 2/5/2018.
 */
@Transactional
@Repository
public class LoginDAOImpl implements LoginDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserBean findByUserName(String username) {
        UserBean userBean = null;
        String hql = "FROM UserBean as user where username = :username";
        try{
            Object obj = entityManager.createQuery(hql).setParameter("username",username).getSingleResult();
            if(obj instanceof UserBean){
                userBean = (UserBean)obj;
            }
        }catch (Exception e){
            System.out.println("User not found");
        }
        return userBean;
    }

    @Override
    public boolean updateToken(UserBean userBean) {
        boolean flag = true;

        try{
            entityManager.flush();
        }catch (Exception e){
            flag = false;
        }

        return flag;
    }
}
