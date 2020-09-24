package com.qlct.mymoney.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qlct.mymoney.Key;
import com.qlct.mymoney.MainActivity;
import com.qlct.mymoney.R;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

public class CreateWalletFragment extends Fragment {
    private View view;
    private Button btnResume;
    private EditText edtWallet;
    private String name;
    private String pin;
    private int wallet;
    private UserDitures userDitures;
    private AddUserDituresViewModel addUserDituresViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create_wallet, container, false);
        initView();

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDitures = new UserDitures(name, Integer.valueOf(edtWallet.getText().toString().trim()), pin);

                new AddUserDituresTask(userDitures).execute();

                openMainScreen();

            }
        });

        addUserDituresViewModel = ViewModelProviders.of(CreateWalletFragment.this).get(AddUserDituresViewModel.class);

        return view;
    }

    private void initView() {
        btnResume = view.findViewById(R.id.btnResume);
        edtWallet = view.findViewById(R.id.edtWallet);

        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString(Key.NAME);
            pin = bundle.getString(Key.PIN);


        }

    }

    private void openMainScreen(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    private class AddUserDituresTask extends AsyncTask<Void, Void, Void> {
        UserDitures userDitures;

        public AddUserDituresTask(UserDitures userDitures) {
            this.userDitures = userDitures;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDituresDB.getUserDituresDB(getActivity().getApplication()).getUserDituresDao().insert(userDitures);
            return null;
        }
    }
}