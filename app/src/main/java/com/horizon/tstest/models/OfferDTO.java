package com.horizon.tstest.models;

import com.google.gson.annotations.SerializedName;

/*By Phat La*/
public class OfferDTO extends DTO {
    @SerializedName("min_amount")
    private double minAmount;

    @SerializedName("max_amount")
    private double maxAmount;

    @SerializedName("min_tenor")
    private int minTenor;

    @SerializedName("max_tenor")
    private int maxTenor;

    @SerializedName("interest_rate")
    private double interestRate;

    private BankDTO bank;


    //
    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getMinTenor() {
        return minTenor;
    }

    public void setMinTenor(int minTenor) {
        this.minTenor = minTenor;
    }

    public int getMaxTenor() {
        return maxTenor;
    }

    public void setMaxTenor(int maxTenor) {
        this.maxTenor = maxTenor;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }
}
