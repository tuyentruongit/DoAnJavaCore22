package entity;

import statics.Gender;

import java.io.Serializable;

public class Person implements Serializable {

    protected String accountName;
    protected String password;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
