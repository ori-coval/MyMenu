package com.example.mymenu;

public class Account {
    private String accName;
    private String accSum;
    private String accTime;
    private String accDate;
    private String accType;

    public Account(String accName, String accSum, String accTime, String accDate, String accType) {
        this.accName = accName;
        this.accSum = accSum;
        this.accTime = accTime;
        this.accDate = accDate;
        this.accType = accType;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccSum() {
        return accSum;
    }

    public void setAccSum(String accSum) {
        this.accSum = accSum;
    }

    public String getAccTime() {
        return accTime;
    }

    public void setAccTime(String accTime) {
        this.accTime = accTime;
    }

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accName='" + accName + '\'' +
                ", accSum='" + accSum + '\'' +
                ", accTime='" + accTime + '\'' +
                ", accDate='" + accDate + '\'' +
                ", accType='" + accType + '\'' +
                '}';
    }
}
