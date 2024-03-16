package com.adminPanel.app.Exception;

import com.adminPanel.app.model.UserRegisterModel;

public class userNotFoundException {

    private boolean status;
    private String message;
    private UserRegisterModel data;

    public userNotFoundException(boolean status , String message , UserRegisterModel data){
//        super(status ,message ,data);
        this.status=status;
        this.message=message;
        this.data=data;

    }
}
//extends Exception