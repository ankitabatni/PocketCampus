package com.example.ankita.pocketcampus.model;

/**
 * Created by ankita on 6/9/17.
 */

public class Professor {

    public String professorId;
    public String schoolName;
    public String firstName;
    public String lastName;

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String departmentName;

    public Professor(String professorId, String schoolName, String firstName, String lastName, String departmentName) {
        this.professorId = professorId;
        this.schoolName = schoolName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentName = departmentName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

