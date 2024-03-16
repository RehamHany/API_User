package com.adminPanel.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user_registration")
@ToString
public class UserRegisterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Column(name = "name")
    private String name;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email Not valid")
    @Column(name = "email")
    private String email;
    @NotEmpty
    @Column(name = "password")
    private String password;
    @NotEmpty
    @Column(name = "confirm_pass")
    private String confirmPass;
    @NotEmpty
    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @OneToOne(mappedBy = "userRegisterModel" , cascade = CascadeType.ALL)
    private UserLoginModel userLoginModel;

    public UserRegisterModel(String name, String email, String password, String confirmPass, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPass = confirmPass;
        this.phone = phone;
    }
    public UserRegisterModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserLoginModel getUserLoginModel() {
        return userLoginModel;
    }

    public void setUserLoginModel(UserLoginModel userLoginModel) {
        this.userLoginModel = userLoginModel;
    }
}
