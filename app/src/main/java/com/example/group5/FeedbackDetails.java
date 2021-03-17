package com.example.group5;

public class FeedbackDetails {

    private String name, message;

    public FeedbackDetails() {
    }

    public FeedbackDetails(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

