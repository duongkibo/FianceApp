package com.qlct.mymoney.base;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;


public abstract class BaseViewModel extends ViewModel {

    public MutableLiveData<Boolean> progressLoading = new MutableLiveData<>();


    protected WeakReference<Context> context;

    public BaseViewModel(Context context) {
        this.context = new WeakReference<>(context);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public MutableLiveData<Boolean> getIsLoading() {
        return progressLoading;
    }

}
