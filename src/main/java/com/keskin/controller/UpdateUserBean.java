package com.keskin.controller;

import com.keskin.model.User;
import com.keskin.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class UpdateUserBean {
    private User user;
    private List<SelectItem> roleList;

    @EJB
    private UserService userService;

    @PostConstruct
    public void init(){
        try {
            roleList = new ArrayList<>();
            User.Role[] roleArr = User.Role.values();

            for (User.Role role : roleArr) {
                roleList.add(new SelectItem(role));
            }

            user = new User();

            HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            int userId = Integer.valueOf(req.getParameter("userId"));
            user = userService.findUser(userId);
        } catch (Exception e) {
            System.out.println("userID is null...");
        }
    }

    public String updateUser(){
        userService.updateUser(this.user);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Update success",
                "  User updated on database!"));
        return "updateuser";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<SelectItem> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SelectItem> roleList) {
        this.roleList = roleList;
    }
}
