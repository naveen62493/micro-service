package com.naveen.authentication.bean;

/**
 * Created by Naveen on 2/5/2018.
 */
public class ResponseBean {

    private String flag;
    private String role;
    private String message;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
