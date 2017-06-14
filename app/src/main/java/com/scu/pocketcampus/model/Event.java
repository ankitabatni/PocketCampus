package com.scu.pocketcampus.model;

/**
 * Created by avinash.kashyap on 6/11/17.
 */

public class Event {
    String name;
    String date;
    String description;
    String type;
    String startTime;
    String endTime;

    public Event(){

    }

    public Event(String name, String date, String description, String type, String startTime, String endTime) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}