package com.adminPanel.app.util;

import java.util.UUID;

public class userCode {
    public static String getCode(){
        return UUID.randomUUID().toString();
    }
}
