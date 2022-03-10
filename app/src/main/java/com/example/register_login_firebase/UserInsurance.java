package com.example.register_login_firebase;

public class UserInsurance {

    String fCompany_name, fBranch_name, fPlan, fPolicy_date, fMaturity_date, fMode_of_premium, fRemark, ftext;//insurance(mainActivity)

    int fPolicy_number, fNo_of_premium, fMaturity_amount, fSum_assured, fPremium_amount;//insurance(mainActivity)

    //Insurance
    public UserInsurance(String fCompany_name,String fBranch_name,String fPlan,
                String fPolicy_date,String fMaturity_date,
                String fMode_of_premium,String fRemark,String ftext,
                int fPolicy_number,int fNo_of_premium,int fMaturity_amount,
                int fSum_assured,int fPremium_amount){
        this.fCompany_name=fCompany_name;
        this.fBranch_name=fBranch_name;
        this.fPlan=fPlan;
        this.fPolicy_date=fPolicy_date;
        this.fPolicy_number=fPolicy_number;
        this.fPremium_amount=fPremium_amount;
        this.fNo_of_premium=fNo_of_premium;
        this.fMaturity_amount=fMaturity_amount;
        this.fMaturity_date=fMaturity_date;
        this.fSum_assured=fSum_assured;
        this.fMode_of_premium=fMode_of_premium;
        this.ftext=ftext;
        this.fRemark=fRemark;

    }

   /* public User(String fCompany_name, String fBranch_name, String fPlan, String fPolicy_date, int parseInt, int parseInt1, int parseInt2, int fPolicy_number, String fMaturity_date, int fMaturity_amount, String fMode_of_premium, String fRemark) {
    }*/


    public String getFCompany_name(){
        return fCompany_name;
    }

    public String getFBranch_name(){
        return fBranch_name;
    }

    public String getFPlan(){
        return fPlan;
    }

    public String getFPolicy_date(){
        return fPolicy_date;
    }

    public int getFPolicy_number(){
        return fPolicy_number;
    }

    public int getFPremium_amount(){
        return fPremium_amount;
    }

    public int getFNo_of_premium() {
        return fNo_of_premium;
    }

    public int getFMaturity_amount(){
        return fMaturity_amount;
    }

    public String getFMaturity_date(){
        return fMaturity_date;
    }

    public int getFSum_assured(){
        return fSum_assured;
    }

    public String getFMode_of_premium(){
        return fMode_of_premium;
    }

    public String getFTEXT(){ return ftext; }

    public String getFRemark(){
        return fRemark;
    }


}

