package com.horizon.tstest;

import android.util.Log;

import com.google.gson.Gson;
import com.horizon.tstest.api.RESTClient;
import com.horizon.tstest.api.RESTService;
import com.horizon.tstest.models.BankDTO;
import com.horizon.tstest.models.LoansDTO;
import com.horizon.tstest.models.OfferDTO;
import com.horizon.tstest.models.ProvincesDTO;
import com.horizon.tstest.rxcall.RXCallHandler;
import com.horizon.tstest.rxcall.RXComposite;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*by Phat La*/
public class MainPresenter implements MainContract.Presenter, RXComposite {
    private static final String TAG = MainPresenter.class.getSimpleName();

    //
    private MainContract.View view;

    //
    private RESTService restService;

    //RX Call
    private CompositeDisposable compositeDisposable;

    //Use to view json response from logcat
    private Gson gson = new Gson();

    @Override
    public CompositeDisposable getCompositeDisposable() {
        if(compositeDisposable == null) compositeDisposable = new CompositeDisposable();
        return compositeDisposable;
    }


    //Constructor
    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.restService = RESTClient.getService();
    }


    //
    @Override
    public void requestOffer() {
        SimpleCall(restService.getOffer(), new RXCallHandler<OfferDTO>() {
            @Override
            public void onSubscribe(Disposable d) {
                //This use for pre-execute
            }

            @Override
            public void onSuccess(OfferDTO s) {
                BankDTO bank = s.getBank();
                view.showBankInfo(bank.getName(), bank.getLogoURL());
                view.showAmountRange(s.getMinAmount(), s.getMaxAmount());
                view.showTenorRange(s.getMinTenor(), s.getMaxTenor());
                view.showInterestRate(s.getInterestRate());

                //
                Log.i(TAG, gson.toJson(s));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.getMessage());
            }

            @Override
            public void onFinally() {}
        });
    }

    @Override
    public void requestProvinces() {
        SimpleCall(restService.getProvinces(), new RXCallHandler<ProvincesDTO>() {
            @Override
            public void onSubscribe(Disposable d) {
                //This use for pre-execute
            }

            @Override
            public void onSuccess(ProvincesDTO s) {
                view.bindProvinces(s.getList());

                //
                Log.i(TAG, gson.toJson(s));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.getMessage());
            }

            @Override
            public void onFinally() {}
        });
    }

    @Override
    public void submitLoans(String fullName, String nationalId, String phoneNumber, String province, double monthlyIncome) {
        //Create Form Data
        Map<String, Object> formData = new HashMap<>();
        formData.put("full_name", fullName);
        formData.put("national_id_number", nationalId);
        formData.put("phone_number", phoneNumber);
        formData.put("province", province);
        formData.put("monthly_income", monthlyIncome);

        //
        SimpleCall(restService.postLoans(formData), new RXCallHandler<LoansDTO>() {
            @Override
            public void onSubscribe(Disposable d) {
                //This use for pre-execute
            }

            @Override
            public void onSuccess(LoansDTO s) {
                view.showToastMessage("Gửi đăng ký vay thành công.");

                //
                Log.i(TAG, gson.toJson(s));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.getMessage());
            }

            @Override
            public void onFinally() {}
        });
    }
}
