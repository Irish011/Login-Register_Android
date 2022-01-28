package com.example.register_login_firebase;

public class User {
    public String email, number, password;

    public User(){
        
    }
    
    public User(String email, String password, String number){
        this.email = email;
        this.password = password;
        this.number = number;
    }
}
