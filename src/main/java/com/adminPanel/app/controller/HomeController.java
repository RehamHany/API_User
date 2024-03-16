package com.adminPanel.app.controller;

import com.adminPanel.app.Exception.Response.UserErrorResponse;
import com.adminPanel.app.Exception.userNotFoundException;
import com.adminPanel.app.model.*;
import com.adminPanel.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private UserService userService;

//    private   mailService mailS;

    @PostMapping("/users")
    public UserErrorResponse Register(@RequestBody UserRegisterModel userRegisterModel) {
        if(! (userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPass())))
            return new UserErrorResponse(false,"Password and Confirm Password must be Same :(",null);
        if(!(userRegisterModel.getPhone().length()==11))
            return new UserErrorResponse(false,"Phone must be 11 number :(",null);
        if(! (userService.checkUserRegister(userRegisterModel).size()==0))
            return new UserErrorResponse(false,"not Exist any Changes :)",null);
        if(! (userRegisterModel.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new UserErrorResponse(false,"Email Not Valid :(",null);



        return new UserErrorResponse(true,"Registration Successfully :)",userService.insert(userRegisterModel));
    }

    @PutMapping("/users")
    public UserErrorResponse updateUser(@RequestBody UserRegisterModel userRegisterModel) {

//        if(! (userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPass())))
//            return new UserErrorResponse(false,"Password and Confirm Password must be Same :(",null);
        if(!(userRegisterModel.getPhone().length()==11))
            return new UserErrorResponse(false,"Phone must be 11 number :(",null);
        if(! (userRegisterModel.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new UserErrorResponse(false,"Email Not Valid :(",null);
        if(! (userService.checkUserRegister(userRegisterModel).size()==0)){
        for(UserRegisterModel user:userService.checkUserRegister(userRegisterModel)){
            if(user.getName()==userRegisterModel.getName()&&user.getPassword()==userRegisterModel.getPassword())
                return new UserErrorResponse(false,"not Exist any Changes :)",null);
        }}
//        if(! (userService.checkUserRegister(userRegisterModel).size()==0))
//            return new UserErrorResponse(false,"not Exist any Changes :)",null);

         userService.update(userRegisterModel.getName(),userRegisterModel.getEmail(),userRegisterModel.getPassword(),userRegisterModel.getPhone());
         return new UserErrorResponse(true,"Updated Successfully :)",userService.findByEmail(userRegisterModel.getEmail()));
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestParam("userId") int id){
        userService.deleteById(id);
    }


    @GetMapping("/users")
    public UserErrorResponse getUserById(@RequestParam("userId") int id) {
        if (id<=0 )
            return new UserErrorResponse(false,"not found user with id <=0 :(",null);
        if(userService.findById(id)==null)
            return new UserErrorResponse(false,"not found user with id : "+id,null);
        return new UserErrorResponse(true,"user Founded",userService.findById(id));
    }




    @PostMapping ("/users/checkUserFound/login")
    public UserErrorResponse Checklogin(@RequestBody UserLoginModel userLoginModel) {
        if(! (userLoginModel.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new UserErrorResponse(false,"Email Not Valid :(",null);



            if (! userService.findEmail(userLoginModel.getEmail())) {
                return new UserErrorResponse(false,"this email doesnot exist in data users :(",null);
            }

       if((userService.checkUserLogin(userLoginModel.getEmail(),userLoginModel.getPassword())).size()==1) {

           System.out.println("UserFound");
           UserRegisterModel userRegisterModel=userService.findByEmail(userLoginModel.getEmail());
           return new UserErrorResponse(true,"Login Successfully",userRegisterModel);

         }else {
           System.out.println("UserNotFound");
           return new UserErrorResponse(false,"password wrong , you are forget Password :(",null);
         }
    }

    @PostMapping ("/users/checkUserFound/register")
    public Boolean CheckRegister(@RequestBody UserRegisterModel userRegisterModel){

        if((userService.checkUserRegister(userRegisterModel)).size()==0){
            System.out.println("UserRegisterd");
            return true;

        }else {
            System.out.println("UserNotRegisterd");
            return false;
        }
    }

    @GetMapping("/users/getAllUsers")
    public List<UserLoginModel> getAllUsers(){
        return userService.getAllUsers();
    }




//    @PostMapping("/checkEmail")
//    public void resetPasswordEmail(@RequestBody ResetPassword resetPassword){
//
//        Mail mail=new Mail (resetPassword.getEmail(), userCode.getCode()) ;
//
//        mailS.sendCodeByMail(mail);
//
//    }

}
