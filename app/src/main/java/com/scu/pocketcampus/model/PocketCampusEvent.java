package com.scu.pocketcampus.model;

/**
 * Created by avinash.kashyap on 6/11/17.
 */

public class PocketCampusEvent {
    String name;
    String date;
    String description;
    String type;
    String startHour;
    String startMinute;
    String endHour;
    String endMinute;

    public PocketCampusEvent() {

    }

    public PocketCampusEvent(String name, String date, String description, String type, String startHour, String startMinute, String endHour, String endMinute) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.type = type;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
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

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(String startMinute) {
        this.startMinute = startMinute;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(String endMinute) {
        this.endMinute = endMinute;
    }
}