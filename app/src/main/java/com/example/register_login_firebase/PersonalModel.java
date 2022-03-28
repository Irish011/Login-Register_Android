package com.example.register_login_firebase;

public class PersonalModel {

    String doc_name;

    public PersonalModel(){}

    public PersonalModel(String doc_name){
        this.doc_name = doc_name;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }
}
