package com.example.register_login_firebase;

public class UserLoker {

    String fBank_nameLoker, fBranch_nameLoker, fItem_descriptionLoker, fDue_dateLoker, fMode_of_operationLoker, fremarkLoker;

    int floker_numberLoker, fYearly_rentLoker;

    public UserLoker(String fBank_nameLoker, String fBranch_nameLoker, String fItem_descriptionLoker,
                     String fDue_dateLoker, String fMode_of_operationLoker, String fremarkLoker,
                     int floker_numberLoker, int fYearly_rentLoker){
        this.fBank_nameLoker =fBank_nameLoker;
        this.fBranch_nameLoker =fBranch_nameLoker;
        this.fItem_descriptionLoker =fItem_descriptionLoker;
        this.fDue_dateLoker =fDue_dateLoker;
        this.fMode_of_operationLoker = fMode_of_operationLoker;
        this.fremarkLoker =fremarkLoker;
        this.floker_numberLoker =floker_numberLoker;
        this.fYearly_rentLoker =fYearly_rentLoker;
    }

    public String getFBank_nameLoker(){return fBank_nameLoker;}

    public String getFBranch_nameLoker(){return fBranch_nameLoker;}

    public String getFItem_descriptionLoker(){return fItem_descriptionLoker;}

    public String getFDue_dateLoker(){return fDue_dateLoker;}

    public String getFMode_of_operationLoker(){return fMode_of_operationLoker;}

    public String getFremarkLoker(){return fremarkLoker;}

    public int getFloker_numberLoker(){return floker_numberLoker;}

    public int getFYearly_rentLoker(){return fYearly_rentLoker;}

}
