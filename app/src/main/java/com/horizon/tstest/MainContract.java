package com.horizon.tstest;

import java.util.List;

/*By Phat La*/
public interface MainContract {
    interface Presenter{
        void requestOffer();
        void requestProvinces();
        void submitLoans(String fullName, String nationalId, String phoneNumber, String province, double monthlyIncome);
    }

    interface View{
        void showToastMessage(String message);
        void showBankInfo(String name, String logoURL);
        void showAmountRange(double min, double max);
        void showTenorRange(int min, int max);
        void showInterestRate(double rate);
        void bindProvinces(List<String> provinces);
    }
}
