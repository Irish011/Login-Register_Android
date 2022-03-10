package com.example.register_login_firebase;

public class Users {

    private String u_email, u_password, h_name, relation;
    private double u_mobile;

    public Users(){

    }

    public Users(String h_name, String u_email, String u_password, double u_mobile, String relation ){

        this.h_name = h_name;
        this.u_email = u_email;
        this.u_password = u_password;
        this.u_mobile = u_mobile;
        this.relation = relation;
    }

    public String getH_name() {return h_name; }

    public String getU_email(){
        return u_email;
    }

    public String getU_password(){
        return u_password;
    }

    public double getU_mobile(){
        return u_mobile;
    }

    public String getRelation() {
        return relation;
    }
}

