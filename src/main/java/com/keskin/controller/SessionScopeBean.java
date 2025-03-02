package com.keskin.controller;

import com.keskin.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionScopeBean {

    private User user;
    private String lang="en";

    public void changeLang(String lang){
        this.lang = lang;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
