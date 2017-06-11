package com.example.ankita.pocketcampus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ankita on 6/9/17.
 */

public class Professor implements Parcelable {

    public String professorId;
    public String schoolName;
    public String firstName;
    public String lastName;

    protected Professor(Parcel in) {
        professorId = in.readString();
        schoolName = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        departmentName = in.readString();
    }

    public static final Creator<Professor> CREATOR = new Creator<Professor>() {
        @Override
        public Professor createFromParcel(Parcel in) {
            return new Professor(in);
        }

        @Override
        public Professor[] newArray(int size) {
            return new Professor[size];
        }
    };

    public String getProfessorId() {
        return professorId;
    }

    public Professor() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(professorId);
        dest.writeString(schoolName);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(departmentName);
    }
}

