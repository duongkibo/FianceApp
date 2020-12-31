package com.qlct.mymoney.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.qlct.mymoney.R;
import com.qlct.mymoney.utils.Constants;
import com.qlct.mymoney.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;


public class DialogManager {

    public static void setSizeDialog(Context mContext, Window window) {
        if (mContext == null || window == null) return;
        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        int screen_width = size.x;
        window.setLayout((int) (screen_width * 0.9), LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
    }

    public static void showDialogNotInternet(Context mContext) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_warning);
        if (dialog.getWindow() != null) {
            setSizeDialog(mContext, dialog.getWindow());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.AnimationDialog;
            AppCompatImageView imgClose = dialog.findViewById(R.id.img_close_dialog);
            AppCompatTextView textMessageDialog = dialog.findViewById(R.id.text_message_dialog);
            textMessageDialog.setText(R.string.msg_no_internet_access);
            imgClose.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
        }
    }




    public static void showDialogConfirm(Context mContext, Drawable icon, String message, String textButton, View.OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_comfirm);
        if (dialog.getWindow() != null) {
            setSizeDialog(mContext, dialog.getWindow());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.AnimationDialog;
            AppCompatButton btnOK = dialog.findViewById(R.id.btn_ok);
            AppCompatTextView textMessage = dialog.findViewById(R.id.text_message_confirm);
            AppCompatImageView imgIcon = dialog.findViewById(R.id.img_icon_dialog);
            textMessage.setText(message);
            btnOK.setText((textButton != null && !Utils.isEmpty(textButton)) ? textButton : mContext.getText(android.R.string.ok).toString());
            imgIcon.setImageDrawable(icon);
            if (onClickListener != null) {
                btnOK.setOnClickListener(onClickListener);
            } else {
                btnOK.setOnClickListener(v -> dialog.dismiss());
            }
            dialog.show();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ((Activity) mContext).runOnUiThread(() -> {
                        if (dialog.isShowing()) {
                            if (onClickListener != null) {
                                btnOK.setOnClickListener(onClickListener);
                            } else {
                                btnOK.setOnClickListener(v -> dialog.dismiss());
                            }
                        }
                    });
                }
            }, Constants.TIME_AUTO_CLOSE_DIALOG);
        }
    }


}
