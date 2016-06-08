package com.pyp.farmcalender.entity;

/**
 * Created by Administrator on 2016/6/3.
 */
public class UserEntity {

    private String username;
    private String password;
    private String email;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
