package com.adminPanel.app.Exception.Response;

import com.adminPanel.app.model.UserRegisterModel;

public class UserErrorResponse {
    private boolean status;
    private String message;
    private UserRegisterModel data;

    public UserErrorResponse(boolean status, String message, UserRegisterModel data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserRegisterModel getData() {
        return data;
    }

    public void setData(UserRegisterModel data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
