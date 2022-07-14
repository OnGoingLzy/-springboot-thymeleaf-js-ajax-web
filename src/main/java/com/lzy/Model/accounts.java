package com.lzy.Model;

public class accounts {
    private String account;
    private String pwd;
    private String name;
    private String email;
    public accounts(){

    }

    public accounts(String account, String pwd, String name, String email) {
        this.account = account;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
