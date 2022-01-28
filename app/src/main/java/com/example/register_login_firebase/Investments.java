package com.example.register_login_firebase;

public class Investments {

    private String company_name, branch_name, plan, premium_date, maturity_date, Remark;

    private double policy_number, premium_amount, no_premium, maturity_amount;

    public Investments(){}

    public Investments(String company_name, String branch_name, String plan, double policy_number, double premium_amount,
                       String premium_date, double no_premium, double maturity_amount, String maturity_date, String Remark){

        this.company_name = company_name;
        this.branch_name = branch_name;
        this.plan = plan;
        this.policy_number = policy_number;
        this.premium_amount = premium_amount;
        this.premium_date = premium_date;
        this.no_premium = no_premium;
        this.maturity_amount = maturity_amount;
        this.maturity_date = maturity_date;
        this.Remark = Remark;
    }

    public String getCompany_name(){return company_name;}
    public String getBranch_name(){return branch_name;}
    public String getPlan(){return plan;}
    public double getPolicy_number(){return policy_number;}
    public double getPremium_amount(){return premium_amount;}
    public String getPremium_date(){return premium_date;}
    public double getNo_premium(){return no_premium;}
    public double getMaturity_amount(){return maturity_amount;}
    public String getMaturity_date(){return maturity_date;}
    public String getRemark(){return Remark;}
}
