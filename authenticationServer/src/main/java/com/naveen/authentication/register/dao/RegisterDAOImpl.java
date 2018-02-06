package com.naveen.authentication.register.dao;

import com.naveen.authentication.bean.UserBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Naveen on 2/5/2018.
 */
@Transactional
@Repository
public class RegisterDAOImpl implements RegisterDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean registerUser(UserBean user) {
        boolean flag = true;
        try {
            entityManager.persist(user);
        } catch(Exception e){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean checkUser(String username) {
        boolean flag = false;
        String hql = "FROM UserBean as user where user.username = :username";
        try{
            Object object = entityManager.createQuery(hql).setParameter("username",username).getSingleResult();
            if(null!=object){
                flag=true;
            }
        }catch (Exception e){
            System.out.println("User Not Found");
        }
    return flag;
    }
}
