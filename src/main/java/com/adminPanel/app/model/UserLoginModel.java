package com.adminPanel.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user_login")
@ToString
public class UserLoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Column(name = "email")
    private String email;
    @NotEmpty
    @Column(name = "password")
    private String password;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "user_register_id")
    private UserRegisterModel userRegisterModel;
    public UserLoginModel(String email,String password) {
        this.password = password;
        this.email = email;
    }

    public UserLoginModel() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRegisterModel getUserRegisterModel() {
        return userRegisterModel;
    }

    public void setUserRegisterModel(UserRegisterModel userRegisterModel) {
        this.userRegisterModel = userRegisterModel;
    }
}
