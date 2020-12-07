package com.qlct.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

public class PinPasswordActivity extends AppCompatActivity {

    private PinEntryEditText pinEntry;
    private UserDituresDB userDituresDB;
    private String strPin = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_password);
        pinEntry = findViewById(R.id.txt_pin_entry);



        AddUserDituresViewModel viewModel = ViewModelProviders.of(this).get(AddUserDituresViewModel.class);
        //  viewMode.getIncome().observe(getActivity(), incomeAdapter::setIncomeDituresList);
        viewModel.getUserDitures().observe(this, new Observer<UserDitures>() {
            @Override
            public void onChanged(UserDitures userDitures) {
                if (userDitures != null) {
                    strPin = userDitures.getPassword().toString();

                    //Toast.makeText(getApplicationContext(), String.valueOf(strPin), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), String.valueOf(userDitures.getWallet()), Toast.LENGTH_SHORT).show();
                }
            }

        });

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(pinEntry, InputMethodManager.SHOW_IMPLICIT);
        if (pinEntry != null) {
            pinEntry.setAnimateText(true);
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {

                    if (str.toString().equals(strPin)) {
                        Toast.makeText(PinPasswordActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        pinEntry.setError(true);
                        Toast.makeText(PinPasswordActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                        pinEntry.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pinEntry.setText(null);
                            }
                        }, 1000);
                    }
                }
            });
        }
    }
}