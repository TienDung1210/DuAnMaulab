package com.example.duanmaulab1.model;

public class User {
    public String UserName;
    public String Password;
    public String PhoneNumber;
    public String FullName;

    public User() {
    }



    public User(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public User(String userName, String password, String phoneNumber, String fullName) {
        UserName = userName;
        Password = password;
        PhoneNumber = phoneNumber;
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }
}
