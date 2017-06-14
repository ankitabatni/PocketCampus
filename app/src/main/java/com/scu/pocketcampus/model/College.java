package com.scu.pocketcampus.model;

/**
 * Created by ankita on 6/1/17.
 */

public class College {
    private String collegeName;
    private String countryName;

    private College(){

    }

    public College(String collegeName, String countryName) {
        this.collegeName = collegeName;
        this.countryName = countryName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}