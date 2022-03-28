package com.example.register_login_firebase;

public class FamilyModel {
    String h_name, u_email, relation;

    public FamilyModel(){}

    public FamilyModel(String h_name, String u_email, String relation){
        this.h_name = h_name;
        this.u_email = u_email;
        this.relation = relation;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String get_relation() { return relation; }

    public void set_relation(String relation) { this.relation = relation; }
}
