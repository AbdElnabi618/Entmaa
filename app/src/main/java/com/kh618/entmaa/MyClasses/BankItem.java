package com.kh618.entmaa.MyClasses;

public class BankItem {
    private int bankBackground;
    private String bankName;
    private String bankAccountNumber;
    private int bankLoog;

    public BankItem(int bankBackground, String bankName, String bankAccountNumber, int bankLoog) {
        this.bankBackground = bankBackground;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.bankLoog = bankLoog;
    }

    public void setBankBackground(int bankBackground) {
        this.bankBackground = bankBackground;
    }

    public int getBankBackground() {
        return bankBackground;
    }

    public String getBankName() {
        return bankName;
    }

    public String getbankAccountNumber() {
        return bankAccountNumber;
    }

    public int getBankLoog() {
        return bankLoog;
    }
}
