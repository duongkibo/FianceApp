package com.qlct.mymoney.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.qlct.mymoney.R;


public abstract class BaseDialog<T extends ViewDataBinding, V> extends Dialog {
    public T binding;

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract int getBindingVariable();

    public abstract void bindingView(T binding);

    public abstract V getData();

    public Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initLayout();
    }

    private void initLayout() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getLayoutId(), null, false);
        setContentView(binding.getRoot());
        binding.setVariable(getBindingVariable(), getData());
        binding.executePendingBindings();

        if (getWindow() != null) {
            Window window = getWindow();
            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getRealSize(size);
            int screen_width = size.x;
            window.setLayout((int) (screen_width * 0.9f), LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.getAttributes().windowAnimations = R.style.AnimationDialog;
        }
        setCancelable(cancelable());

        bindingView(binding);
    }

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }


    protected boolean cancelable() {
        return true;
    }
}

