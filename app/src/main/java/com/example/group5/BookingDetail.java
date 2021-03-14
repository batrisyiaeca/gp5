package com.example.group5;

public class BookingDetail {

    private String name, email, phone , room, date;

    public BookingDetail(){

    }

    public BookingDetail(String name, String email, String phone, String room, String date){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.room = room;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
