package com.example.register_login_firebase;

public class UserOI {

    String fCompany_NameOI, fholder_nameOI, finvestment_dateOI,
            fMaturity_dateOI, finvestment_typeOI, fRemarkOI;

    int faccount_numberOI, famountOI;

    public UserOI(String fCompany_NameOI, String fholder_nameOI, String finvestment_dateOI,
                  String fMaturity_dateOI, String finvestment_typeOI, String fRemarkOI,
                  int  faccount_numberOI, int famountOI){
        this.fCompany_NameOI =fCompany_NameOI;
        this.faccount_numberOI =faccount_numberOI;
        this.fholder_nameOI =fholder_nameOI;
        this.finvestment_dateOI =finvestment_dateOI;
        this.fMaturity_dateOI =fMaturity_dateOI;
        this.finvestment_typeOI =finvestment_typeOI;
        this.famountOI =famountOI;
        this.fRemarkOI =fRemarkOI;
    }

    public String getFCompany_NameOI() {
        return fCompany_NameOI;
    }

    public String getFholder_nameOI() {
        return fholder_nameOI;
    }

    public int getFaccount_numberOI() {
        return faccount_numberOI;
    }

    public String getFinvestment_dateOI() {
        return finvestment_dateOI;
    }

    public int getFamountOI() {
        return famountOI;
    }

    public String getFMaturity_dateOI() {
        return fMaturity_dateOI;
    }

    public String getFinvestment_typeOI() {
        return finvestment_typeOI;
    }

    public String getfRemarkOI() {
        return fRemarkOI;
    }
}

