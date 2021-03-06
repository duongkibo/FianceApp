package com.qlct.mymoney.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.qlct.mymoney.BR;
import com.qlct.mymoney.R;
import com.qlct.mymoney.base.BaseDialog;
import com.qlct.mymoney.databinding.DialogMessageBinding;
import com.qlct.mymoney.utils.Utils;


public class MessageDialog extends BaseDialog<DialogMessageBinding, ContentDialog> implements View.OnClickListener {
    private ContentDialog contentDialog;

    public MessageDialog(@NonNull Context context, String title, String message, String messageDefault) {
        super(context);
        this.context = context;
        this.contentDialog = new ContentDialog(Utils.isEmpty(title) ? context.getString(R.string.dialog_confirm) : title,
                null,
                Utils.isEmpty(message) ? messageDefault : message,
                null,
                null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_message;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void bindingView(DialogMessageBinding binding) {
        binding.layoutTitle.imgClose.setOnClickListener(this);
    }


    @Override
    public ContentDialog getData() {
        return contentDialog;
    }

    @Override
    protected boolean cancelable() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgClose:
                dismiss();
                break;
        }
    }
}
