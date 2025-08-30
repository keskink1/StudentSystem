package com.keskin.controller;

import com.keskin.model.User;
import com.keskin.service.UserService;
import com.keskin.util.FacesMessageUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
public class UserListBean {

    private List<User> userList;


    @EJB
    private UserService userService;

    @PostConstruct
    public void init(){
        userList = userService.getAllUsers();
    }


    public String deleteUser(User user){
        userService.deleteUser(user);
        FacesMessageUtil.addInfo("Delete success", "User deleted from database!");

        return "userlist?faces-redirect=true";
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
