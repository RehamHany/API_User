package com.adminPanel.app.dao;

import com.adminPanel.app.model.UserLoginModel;
import com.adminPanel.app.model.UserRegisterModel;

import java.util.List;

public interface UserDAO {
    UserLoginModel insert (UserRegisterModel userRegisterModel);

    void update (String name,String email,String pass,String phone);

    void deleteById (int id);

    UserRegisterModel findById(int id);

    UserLoginModel findByUserRegister(UserRegisterModel userRegisterModel);

    List<UserLoginModel> getAllUsers();
    List<UserLoginModel> checkUserLogin(String email,String pass);

    List<UserRegisterModel> checkUserRegister(UserRegisterModel userRegisterModel);

    UserRegisterModel findByEmail(String email);

    UserLoginModel findByEmail1(String email);

    List<UserLoginModel> findEmail(String email);

}
