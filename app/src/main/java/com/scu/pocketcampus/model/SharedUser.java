package com.scu.pocketcampus.model;

/**
 * Created by ankita on 6/1/17.
 */

public class SharedUser {
    public String userId;
    public String firstName;
    public String lastName;
    public String email;
    public String college;
    public String country;

    public static SharedUser user;

    private SharedUser(){

    }

    private SharedUser(String userId, String firstName, String lastName, String email, String college, String country) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.college = college;
        this.country = country;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static SharedUser getInstance(){
        if(user == null){
            user = new SharedUser();
            return user;
        }

        return user;
    }

    public static SharedUser createFreshUser(){
        user = new SharedUser();
        return user;
    }

}
