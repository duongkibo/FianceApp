package com.qlct.mymoney.base;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.qlct.mymoney.authenticate.LoginViewModel;


public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;

    public ViewModelProviderFactory(Context context) {
        this.context = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}