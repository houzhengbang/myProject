package com.hzb.test.entity;

public class LoginBody {

//    public String userAccount;
//    public String newPassword;


    private String parentAccount;
    private int loginMethod;
    private String parentPassword;
    private String code;

    public LoginBody(String parentAccount,  String parentPassword,String code) {
        this.parentAccount = parentAccount;
        this.parentPassword = parentPassword;
        this.code = code;
    }

    /*  public LoginBody(String userAccount, String newPassword) {
        this.userAccount = userAccount;
        this.newPassword = newPassword;
    }

    public LoginBody() {
    }*/
}
