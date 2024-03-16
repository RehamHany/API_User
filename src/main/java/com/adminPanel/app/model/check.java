package com.adminPanel.app.model;

public class check {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public check(String message) {
        this.message = message;
    }
    public check() {

    }

    @Override
    public String toString() {
        return "check{" +
                "message='" + message + '\'' +
                '}';
    }
}
