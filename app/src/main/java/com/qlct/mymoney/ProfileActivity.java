package com.qlct.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.qlct.mymoney.model.DataBaseIntalizerUser;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgProfileClose;
    private EditText edtChangeName;
    private EditText oldPinEntry;
    private EditText newPinEntry;
    private EditText newAgainPinEtry;
    private Button btnSave;
    private AddUserDituresViewModel addUserDituresViewModel;

    private int pinOld = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();
    }

    private void initView() {
        imgProfileClose = findViewById(R.id.imgprofile_close);
        edtChangeName = findViewById(R.id.edtChange_name);
        // oldPinEntry = findViewById(R.id.edtPin_old);
        newPinEntry = findViewById(R.id.edtPin_new);
        // newAgainPinEtry = findViewById(R.id.edtPin_new_again);
        btnSave = findViewById(R.id.btnSave);
        imgProfileClose.setOnClickListener(this::onClick);

        addUserDituresViewModel = ViewModelProviders.of(ProfileActivity.this).get(AddUserDituresViewModel.class);


        DataBaseIntalizerUser.populateAsync(UserDituresDB.getUserDituresDB(getApplicationContext()));
        AddUserDituresViewModel viewModel = ViewModelProviders.of(this).get(AddUserDituresViewModel.class);
        viewModel.getUserDitures().observe(this, new Observer<UserDitures>() {
            @Override
            public void onChanged(UserDitures userDitures) {
                if (userDitures != null) {
                    pinOld = Integer.parseInt(userDitures.getPassword());
                    edtChangeName.setText(userDitures.getUsername());
                    Toast.makeText(getApplicationContext(), String.valueOf(pinOld), Toast.LENGTH_SHORT).show();


                    /*String strPinOld = String.valueOf(oldPinEntry.getText());
                    if (strPinOld.equals(String.valueOf(pinOld))) {
                        Toast.makeText(getApplicationContext(), "sdfsf", Toast.LENGTH_SHORT).show();
                    }*/

                }
            }

        });


        String name = edtChangeName.getText().toString();
        String password = String.valueOf(newPinEntry.getText());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDitures userDitures = new UserDitures(name, 4642213, password);
                new updateUserAsyncTask(userDitures).execute();
                Toast.makeText(getApplicationContext(), "change...", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private class updateUserAsyncTask extends AsyncTask<Void, Void, Void> {
        UserDitures userDitures;

        public updateUserAsyncTask(UserDitures userDitures) {
            this.userDitures = userDitures;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDituresDB.getUserDituresDB(getApplicationContext()).getUserDituresDao().update(userDitures);
            return null;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgprofile_close:
                onBackPressed();
                break;


        }
    }

}