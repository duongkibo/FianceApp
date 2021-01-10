package com.qlct.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.qlct.mymoney.fragment.AccountFragment;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

import me.aflak.libraries.callback.FingerprintDialogSecureCallback;
import me.aflak.libraries.callback.PasswordCallback;
import me.aflak.libraries.dialog.FingerprintDialog;
import me.aflak.libraries.dialog.PasswordDialog;
import me.aflak.libraries.utils.FingerprintToken;

public class PinPasswordActivity extends AppCompatActivity implements FingerprintDialogSecureCallback, PasswordCallback {

    private PinEntryEditText pinEntry;
    private UserDituresDB userDituresDB;
    private String strPin = "";
    public ImageView imgFinger;
    private AccountFragment accountFragment = new AccountFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_password);
        pinEntry = findViewById(R.id.txt_pin_entry);
        imgFinger = findViewById(R.id.img_pin_fingerprint);


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

        imgFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FingerprintDialog.isAvailable(PinPasswordActivity.this)) {
                    FingerprintDialog.initialize(PinPasswordActivity.this)
                            .title(R.string.fingerprint_title)
                            .message(R.string.fingerprint_message)
                            .callback(PinPasswordActivity.this, "KeyName1")
                            .show();
                }
            }
        });
    }




    @Override
    public void onAuthenticationSucceeded() {
        Intent intent = new Intent(PinPasswordActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAuthenticationCancel() {

    }

    @Override
    public void onNewFingerprintEnrolled(FingerprintToken token) {
        PasswordDialog.initialize(this, token)
                .title(R.string.password_title)
                .message(R.string.password_message)
                .callback(this)
                .passwordType(PasswordDialog.PASSWORD_TYPE_TEXT)
                .show();
    }

    @Override
    public void onPasswordSucceeded() {

    }

    @Override
    public boolean onPasswordCheck(String password) {
        return password.equals("password");
    }

    @Override
    public void onPasswordCancel() {

    }


}