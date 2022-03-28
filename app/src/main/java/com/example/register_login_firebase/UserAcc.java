package com.example.register_login_firebase;

public class UserAcc {

    String fBank_name, fHolder_name, fMode_of_Operation, fMaturity_date, fRemark, fBranch_name;
    String fAccount_number, fAmount;

    public UserAcc(String fBank_name, String fBranch_name, String fRemark,
                   String fHolder_name, String fMode_of_Operation, String fMaturity_date,
                   String fAccount_number, String fAmount){
        this.fBank_name=fBank_name;
        this.fAccount_number=fAccount_number;
        this.fAmount=fAmount;
        this.fHolder_name=fHolder_name;
        this.fMode_of_Operation = fMode_of_Operation;
        this.fBranch_name=fBranch_name;
        this.fMaturity_date=fMaturity_date;
        this.fRemark=fRemark;
    }

    public String getFBank_name(){return fBank_name;}

    public String getFHolder_name(){return fHolder_name;}

    public String getFMode_of_Operation(){return fMode_of_Operation;}

    public String getFAccount_number(){return fAccount_number;}

    public String getFAmount(){return fAmount;}

    public String getFBranch_name(){
        return fBranch_name;
    }

    public String getFMaturity_date(){
        return fMaturity_date;
    }

    public String getFRemark(){
        return fRemark;
    }

}
