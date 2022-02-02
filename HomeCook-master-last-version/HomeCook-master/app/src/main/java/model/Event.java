package model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Event implements Serializable {

    private String id;
    private String category;
    private String description;
    private String eventName;
    private String startDate;
    private String endDate;
    private String photo;
    private double price;
    private String userName;



    public Event(String id, String category, String description, String eventName, String startDate, String endDate, String photo, double price, String userName) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.photo = photo;
        this.price = price;
        this.userName = userName;



    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
