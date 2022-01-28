package com.example.register_login_firebase;

public class Users {

    private String u_email, u_password;
    private double u_mobile;

    public Users(){

    }

    public Users(String u_email, String u_password, double u_mobile ){
        this.u_email = u_email;
        this.u_password = u_password;
        this.u_mobile = u_mobile;
    }

    public String getU_email(){
        return u_email;
    }

    public String getU_password(){
        return u_password;
    }

    public double getU_mobile(){
        return u_mobile;
    }
}

