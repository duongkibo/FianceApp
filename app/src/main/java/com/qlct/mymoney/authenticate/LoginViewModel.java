package com.qlct.mymoney.authenticate;

import android.content.Context;

import com.mukesh.OnOtpCompletionListener;
import com.qlct.mymoney.base.BaseViewModel;


public class LoginViewModel extends BaseViewModel implements OnOtpCompletionListener {

    public LoginViewModel(Context context) {
        super(context);
    }


    public void onSignIn(){

    }

    public void onDismiss(){

    }

    public void onClearPhoneNumber(){

    }

    @Override
    public void onOtpCompleted(String otp) {

    }
}
