package com.example.register_login_firebase;

public class Usercc {

    String fbank_nameCC, fcard_typeCC, fvalidatyCC, frenewal_amountCC, fRemarkCC;
    int  fcard_number, flimit;

    public Usercc(String fbank_nameCC, String fcard_typeCC, String fvalidatyCC, String fRemarkCC,
                  String frenewal_amountCC, int fcard_numberCC, int flimitCC){
        this.fbank_nameCC =fbank_nameCC;
        this.fcard_number=fcard_numberCC;
        this.fcard_typeCC =fcard_typeCC;
        this.flimit=flimitCC;
        this.fvalidatyCC =fvalidatyCC;
        this.frenewal_amountCC =frenewal_amountCC;
        this.fRemarkCC =fRemarkCC;
    }

    public String getFbank_nameCC() { return fbank_nameCC; }

    public int getFcard_numberCC() { return fcard_number;}

    public String getFcard_typeCC() { return fcard_typeCC; }

    public int getFlimitCC() { return flimit;}

    public String getFvalidatyCC() { return fvalidatyCC;}

    public String getFrenewal_amountCC() { return frenewal_amountCC;}

    public String getfRemarkCC() { return fRemarkCC;}
}
