package model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User {

    private String first_name,last_name,userName,email,postal_code,address,password,date_of_birth,isHomeCook;

    private int apartment;
    private ArrayList<String> events;

    public User(String first_name, String last_name, String userName, String email, String postal_code, String address, String password, String date_of_birth, String isHomeCook, int apartment, ArrayList<String> events) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.userName = userName;
        this.email = email;
        this.postal_code = postal_code;
        this.address = address;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.isHomeCook = isHomeCook;
        this.apartment = apartment;
        this.events = events;
    }

    public String getIsHomeCook() {
        return isHomeCook;
    }

    public void setIsHomeCook(String isHomeCook) {
        this.isHomeCook = isHomeCook;
    }

    public User(String userName, String password) {
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public String toString() {
        return first_name;
    }
}
