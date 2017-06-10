package com.example.ankita.pocketcampus.model;

/**
 * Created by ankita on 6/10/17.
 */

public class Rate {
    public String profId,userId;
    public String courseCode;
    public Float rating;
    public Integer difficultyLevel;
    public boolean takeCourseAgain;
    public String comment;

    public Rate(String profId, String userId, String courseCode, Float rating, Integer difficultyLevel, boolean takeCourseAgain, String comment) {
        this.profId = profId;
        this.userId = userId;
        this.courseCode = courseCode;
        this.rating = rating;
        this.difficultyLevel = difficultyLevel;
        this.takeCourseAgain = takeCourseAgain;
        this.comment = comment;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public boolean isTakeCourseAgain() {
        return takeCourseAgain;
    }

    public void setTakeCourseAgain(boolean takeCourseAgain) {
        this.takeCourseAgain = takeCourseAgain;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
