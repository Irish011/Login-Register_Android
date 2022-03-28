package com.example.register_login_firebase;

public class UserLoan {

    String fbank_nameLoan, fbranch_nameLoan, fdisbursement_dateLoan, fend_dateLoan, fremarkLoan;

    String floan_amountLoan;
    String floan_account_numberLoan;
    String floan_EMILoan;
    String fRoiLoan;

    public UserLoan(String fbank_nameLoan, String fbranch_nameLoan, String fdisbursement_dateLoan,
                    String fend_dateLoan, String fremarkLoan, String floan_amountLoan, String floan_account_numberLoan,
                    String floan_EMILoan, String fRoiLoan){
        this.fbank_nameLoan =fbank_nameLoan;
        this.fbranch_nameLoan =fbranch_nameLoan;
        this.fdisbursement_dateLoan =fdisbursement_dateLoan;
        this.fend_dateLoan =fend_dateLoan;
        this.fremarkLoan =fremarkLoan;
        this.floan_amountLoan =floan_amountLoan;
        this.floan_account_numberLoan =floan_account_numberLoan;
        this.floan_EMILoan =floan_EMILoan;
        this.fRoiLoan =fRoiLoan;
    }

    public String getFbank_nameLoan(){return fbank_nameLoan;}

    public String getFbranch_nameLoan(){return fbranch_nameLoan;}

    public String getFdisbursement_dateLoan(){return fdisbursement_dateLoan;}

    public  String getFend_dateLoanLoan(){return fend_dateLoan;}

    public  String getFremarkLoan(){return fremarkLoan;}

    public String getFloan_amountLoan(){return floan_amountLoan;}

    public String getFloan_account_numberLoan(){return floan_account_numberLoan;}

    public String getFloan_EMILoan(){return floan_EMILoan;}

    public String getFRoiLoan(){return fRoiLoan;}

}
