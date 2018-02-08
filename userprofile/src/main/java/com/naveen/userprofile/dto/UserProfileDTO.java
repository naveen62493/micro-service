package com.naveen.userprofile.dto;

/**
 * Created by Naveen on 2/8/2018.
 */
public final class UserProfileDTO {

    private String id;

    private String fisrtName;

    private String lastName;

    private int age;

    public UserProfileDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "id='" + id + '\'' +
                ", fisrtName='" + fisrtName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
