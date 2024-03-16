package com.adminPanel.app.service;

import com.adminPanel.app.model.UserLoginModel;
import com.adminPanel.app.model.UserRegisterModel;

import java.util.List;

public interface UserService {
    UserRegisterModel insert (UserRegisterModel userRegisterModel);

    UserRegisterModel update (String name,String email,String pass,String phone);

    void deleteById (int id);

    UserRegisterModel findById(int id);

    List<UserLoginModel> getAllUsers();

    List<UserLoginModel> checkUserLogin(String email ,String Pass);
    List<UserRegisterModel> checkUserRegister(UserRegisterModel userRegisterModel);
    UserRegisterModel findByEmail(String email);

    Boolean findEmail(String email);
}
