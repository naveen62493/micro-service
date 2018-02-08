package com.naveen.userprofile.bean;

import org.springframework.data.annotation.Id;

/**
 * Created by Naveen on 2/7/2018.
 */
public final class UserProfile {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private int age;

    public UserProfile(UserProfileBuilder userDetailBuilder) {
        this.firstName = userDetailBuilder.firstName;
        this.lastName = userDetailBuilder.lastName;
        this.age = userDetailBuilder.age;
    }

    public UserProfile() {
    }

    public static UserProfileBuilder getUserDetailBuilder() {
        return new UserProfileBuilder();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public void update(String firstName,String lastName,int age){
            this.firstName=firstName;
            this.lastName=lastName;
            this.age=age;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public static class UserProfileBuilder{
        private String firstName;
        private String lastName;
        private int age;

        private UserProfileBuilder(){

        }

        public UserProfileBuilder setFirstName(String firstName){
            this.firstName=firstName;
            return this;
        }
        public UserProfileBuilder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public UserProfileBuilder setAge(int age){
            this.age = age;
            return this;
        }

        public UserProfile build(){
            UserProfile userProfile = new UserProfile(this);
            return  userProfile;
        }


    }

}
