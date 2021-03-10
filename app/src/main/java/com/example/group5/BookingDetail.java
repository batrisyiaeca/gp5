package com.example.group5;

public class BookingDetail {

    private String name, email, phone , time, date;

    public BookingDetail(){

    }

    public BookingDetail(String name, String email, String phone, String time, String date){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.time = time;
        this.date = date;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
