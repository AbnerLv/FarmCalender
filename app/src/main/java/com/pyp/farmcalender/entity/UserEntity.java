package com.pyp.farmcalender.entity;

/**
 * Created by Administrator on 2016/6/3.
 */
public class UserEntity {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private String profile;
    private String phone;
    private String city;
    private Integer age;
    private Integer sex;
    private String address;



    public UserEntity() {

    }
    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public UserEntity(Integer id, String username, String email,
                      String password, String profile, String phone, String city,
                      Integer age, Integer sex, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.phone = phone;
        this.city = city;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

}
