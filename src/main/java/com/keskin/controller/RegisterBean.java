package com.keskin.controller;

import com.keskin.exception.EmailAlreadyExistsException;
import com.keskin.model.User;
import com.keskin.service.UserService;
import com.keskin.util.FacesMessageUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "register")
public class RegisterBean {
    private User user;
    private String password2;
    private List<SelectItem> roleList;

    @EJB
    private UserService userService;


    @PostConstruct
    public void init(){
        user = new User();
        roleList = new ArrayList<>();
        User.Role[] roleArr = User.Role.values();

        for (User.Role role : roleArr) {
            roleList.add(new SelectItem(role));
        }
    }

    public String registerUser(){

        if(!user.getPassword().equals(password2)){
            FacesMessageUtil.addError("Register error", "Passwords are not matching !");
        } else {

            try {
                userService.registerUserToDatabase(user);
                FacesMessageUtil.addInfo("Register success","User saved to database !");

            } catch (EmailAlreadyExistsException e) {
                FacesMessageUtil.addWarn("Register not executed","Email already exists!");
            }
        }

        return "register";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public List<SelectItem> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SelectItem> roleList) {
        this.roleList = roleList;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
