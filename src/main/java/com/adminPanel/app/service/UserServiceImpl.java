package com.adminPanel.app.service;

import com.adminPanel.app.Exception.userNotFoundException;
import com.adminPanel.app.dao.UserDAO;
import com.adminPanel.app.model.UserLoginModel;
import com.adminPanel.app.model.UserRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserRegisterModel insert(UserRegisterModel userRegisterModel)  {
        if(!userRegisterModel.getPassword().isEmpty() && !userRegisterModel.getEmail().isEmpty()) {
            if((userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPass()))&&(userRegisterModel.getPhone().length()==11)&&(userDAO.checkUserRegister(userRegisterModel).size()==0)){
                userDAO.insert(userRegisterModel);
                return userRegisterModel;
            }
        }
        else
            throw new NullPointerException();
        return null;
    }

    @Override
    public UserRegisterModel update(String name,String email,String pass,String phone) {
        UserLoginModel userLoginModel = userDAO.findByEmail1(email);
        if (userLoginModel != null)
        {
            userDAO.update(name,email,pass,phone);
        }
        else
            throw new NullPointerException();
        return userDAO.findByEmail(email);
    }

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    @Override
    public UserRegisterModel findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public List<UserLoginModel> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserLoginModel> checkUserLogin(String email ,String Pass) {
        return userDAO.checkUserLogin(email,Pass);
    }

    @Override
    public List<UserRegisterModel> checkUserRegister(UserRegisterModel userRegisterModel) {
        return userDAO.checkUserRegister(userRegisterModel);
    }

    @Override
    public UserRegisterModel findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
    @Override
    public Boolean findEmail( String email){


            if (userDAO.findEmail(email).size()==1) {
                return true;
            }

        return false;
    }
}
