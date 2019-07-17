package com.horizon.tstest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.horizon.tstest.utils.DialogUtils;
import com.horizon.tstest.utils.Utils;
import com.horizon.tstest.validators.PhoneValidator;

import java.util.List;


/*By Phat La*/
public class MainActivity extends AppCompatActivity implements MainContract.View{
    private static final String TAG = MainActivity.class.getSimpleName();

    //Views
    private ImageView imgLogo;
    private TextView tvBankName;
    private TextView tvAmountRange;
    private TextView tvTenorRange;
    private TextView tvInterestRate;
    private EditText edtFullName;
    private EditText edtPhoneNumber;
    private EditText edtNationalId;
    private EditText edtMonthlyIncome;
    private Spinner spnProvinces;
    private Button btnSubmit;

    //
    private MainContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        presenter = new MainPresenter(this);

        //Bind Views
        imgLogo             = findViewById(R.id.imgLogo);
        tvBankName          = findViewById(R.id.tvBankName);
        tvAmountRange       = findViewById(R.id.tvAmountRange);
        tvTenorRange        = findViewById(R.id.tvTenorRange);
        tvInterestRate      = findViewById(R.id.tvInterestRate);
        edtFullName         = findViewById(R.id.edtFullName);
        edtPhoneNumber      = findViewById(R.id.edtPhoneNumber);
        edtNationalId       = findViewById(R.id.edtNationalId);
        edtMonthlyIncome    = findViewById(R.id.edtMonthlyIncome);
        spnProvinces        = findViewById(R.id.spnProvinces);
        btnSubmit           = findViewById(R.id.btnSubmit);

        //Setup Events
        btnSubmit.setOnClickListener(this::onButtonSubmitClick);

        //All ready, get offer from server
        presenter.requestOffer();
        presenter.requestProvinces();
    }

    //region Widget event
    private void onButtonSubmitClick(View v){
        String fullName      = edtFullName.getText().toString();
        String phoneNumber   = edtPhoneNumber.getText().toString();
        String nationalId    = edtNationalId.getText().toString();
        String province      = spnProvinces.getSelectedItem().toString();
        String monthlyIncome = edtMonthlyIncome.getText().toString();

        //Full name required
        if(TextUtils.isEmpty(fullName)){
            DialogUtils.createMessageDialog(this, getString(R.string.msg_full_name_is_required)).show();
            return;
        }

        //Phone number required
        if(TextUtils.isEmpty(phoneNumber)){
            DialogUtils.createMessageDialog(this, getString(R.string.msg_phone_number_is_required)).show();
            return;
        }

        //Validate phone number
        if(!PhoneValidator.isValid(phoneNumber)){
            DialogUtils.createMessageDialog(this, getString(R.string.msg_phone_number_is_invalid)).show();
            return;
        }

        //Has NationalId and its length must be greater than 9
        if(!TextUtils.isEmpty(nationalId) && nationalId.length() < 9 ){
            DialogUtils.createMessageDialog(this, getString(R.string.msg_national_id_not_in_range)).show();
            return;
        }

        //Monthly income required
        if(TextUtils.isEmpty(monthlyIncome)){
            DialogUtils.createMessageDialog(this, getString(R.string.msg_monthly_income_is_required)).show();
            return;
        }

        //Province required
        if(TextUtils.isEmpty(province)){
            DialogUtils.createMessageDialog(this, getString(R.string.msg_province_is_required)).show();
            return;
        }

        //Check min monthly income
        double monthlyIncomeAmount = Double.parseDouble(monthlyIncome); //The input just accept number only, so we don't need to catch exception
        if(monthlyIncomeAmount < AppConfig.MIN_MONTHLY_INCOME ){
            DialogUtils.createMessageDialog(this,
                    String.format(getString(R.string.msg_monthly_income_must_be_greater),
                    AppConfig.MIN_MONTHLY_INCOME)).show();
            return;
        }

        //All passed, push to presenter to process
        presenter.submitLoans(fullName, nationalId, phoneNumber, province, monthlyIncomeAmount);

        //
        Utils.hideSoftKeyboard(this);
    }
    //endregion


    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //region View's Behaviours
    @Override
    public void showBankInfo(String name, String logoURL) {
        tvBankName.setText(name);
        Utils.loadImage(imgLogo, logoURL);
    }

    @Override
    public void showAmountRange(double min, double max) {
        tvAmountRange.setText(String.format(getString(R.string.format_amount_range), min, max));
    }

    @Override
    public void showTenorRange(int min, int max) {
        tvTenorRange.setText(String.format(getString(R.string.format_tenor_range), min, max));
    }

    @Override
    public void showInterestRate(double rate) {
        tvInterestRate.setText(String.format(getString(R.string.format_interest_rate), rate));
    }

    @Override
    public void bindProvinces(List<String> provinces) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProvinces.setAdapter(adapter);
    }
    //endregion
}
