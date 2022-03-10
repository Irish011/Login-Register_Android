package com.example.register_login_firebase;

public class ManageModel {

    String b_name, amount;

    public ManageModel(){}

    public ManageModel(String b_name, String amount){
        this.b_name = b_name;
        this.amount = amount;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
