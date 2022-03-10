package com.example.register_login_firebase;

public class UserFD {

    String fbank_nameFD, fBranch_nameFD, fholder_nameFD, finvestment_dateFD,
            fmaturity_dateFD, fmode_of_interestFD, fMode_of_oprationFD, fRemarkFD;

    int finvestment_amountFD, faccount_numberFD, fmaturity_amountFD, finterest_rateFD;

    public UserFD(String fbank_nameFD, String fBranch_nameFD, String fholder_nameFD,
                  String finvestment_dateFD, String fmaturity_dateFD, String fmode_of_interestFD,
                  String fMode_of_oprationFD, String fRemarkFD, int finvestment_amountFD,
                  int faccount_numberFD, int fmaturity_amountFD, int finterest_rateFD) {
        this.fbank_nameFD = fbank_nameFD;
        this.fBranch_nameFD = fBranch_nameFD;
        this.fholder_nameFD = fholder_nameFD;
        this.finvestment_dateFD = finvestment_dateFD;
        this.fmaturity_dateFD = fmaturity_dateFD;
        this.fmode_of_interestFD = fmode_of_interestFD;
        this.fMode_of_oprationFD = fMode_of_oprationFD;
        this.fRemarkFD = fRemarkFD;
        this.finvestment_amountFD = finvestment_amountFD;
        this.faccount_numberFD = faccount_numberFD;
        this.fmaturity_amountFD = fmaturity_amountFD;
        this.finterest_rateFD = finterest_rateFD;
    }


    public String getFbank_nameFD() {
        return fbank_nameFD;
    }

    public String getFBranch_nameFD() {
        return fBranch_nameFD;
    }

    public String getFholder_nameFD() {
        return fholder_nameFD;
    }

    public String getFinvestment_dateFD() {
        return finvestment_dateFD;
    }

    public String getFmaturity_dateFD() {
        return fmaturity_dateFD;
    }

    public String getFmode_of_interestFD() {
        return fmode_of_interestFD;
    }

    public String getFMode_of_oprationFD() {
        return fMode_of_oprationFD;
    }

    public String getFRemarkFD() {
        return fRemarkFD;
    }

    public int getFaccount_numberFD() {
        return faccount_numberFD;
    }

    public int getFinvestment_amountFD() {
        return finvestment_amountFD;
    }

    public int getFmaturity_amountFD() {
        return fmaturity_amountFD;
    }

    public int getFinterest_rateFD() {
        return finterest_rateFD;
    }
}