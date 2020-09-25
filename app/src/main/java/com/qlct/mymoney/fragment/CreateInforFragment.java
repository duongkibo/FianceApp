package com.qlct.mymoney.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.qlct.mymoney.CreateUserActivity;
import com.qlct.mymoney.R;
import com.qlct.mymoney.model.UserDitures;

public class CreateInforFragment extends Fragment {

    private View view;
    private Button btnRegister;
    private EditText edtFirtname;
    private EditText edtPin;
    private EditText editPinAgain;
    private UserDitures userDitures;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create_infor, container, false);
        initView();
        return view;

    }

    private void initView() {
        btnRegister = view.findViewById(R.id.btnRegister);
        edtFirtname = view.findViewById(R.id.edtFirtname);
        edtPin = view.findViewById(R.id.edtPin);
        editPinAgain = view.findViewById(R.id.edtPinAgain);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity fragmentActivity = getActivity();
                if (fragmentActivity instanceof CreateUserActivity) {
                    String strPin = String.valueOf(edtPin.getText());
                    String strFirtname = String.valueOf(edtFirtname.getText());
                    String strPinAgain = String.valueOf(editPinAgain.getText());

                    if (!strFirtname.isEmpty() && !strPin.isEmpty() && !strPinAgain.isEmpty()){
                        if (strPin.equals(strPinAgain)){
                            ((CreateUserActivity) fragmentActivity).setName(strFirtname);
                            ((CreateUserActivity) fragmentActivity).setPin(strPin);

                            ((CreateUserActivity) fragmentActivity).openWalletUserScreen();
                        }
                    }

                }


            }
        });
    }
}