package com.vaccinationapp.Models;

public class UserPojo {

    private String name;
    private String email;
    private String pass;

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserPojo(String name, String email, String pass) {
        name = name;
        email = email;
        pass = pass;
    }

    public UserPojo()
    {

    }

}
