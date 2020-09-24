package com.qlct.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.qlct.mymoney.fragment.CreateInforFragment;
import com.qlct.mymoney.fragment.CreateWalletFragment;

public class CreateUserActivity extends AppCompatActivity {
    private CreateInforFragment createInforFragment;
    private CreateWalletFragment createWalletFragment;
    private String name;
    private String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openInforUserScreen();
    }

    public void openInforUserScreen() {
        if (createInforFragment == null) {
            createInforFragment = new CreateInforFragment();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.bottom_animation, R.anim.bottom_animation);
        fragmentTransaction.replace(android.R.id.content, createInforFragment)
                .commit();
    }

    public void openWalletUserScreen() {
        if (createWalletFragment == null) {
            createWalletFragment = new CreateWalletFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putString(Key.NAME, name);
        bundle.putString(Key.PIN, pin);

        createWalletFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.left_to_right_animation, R.anim.left_to_right_animation);
        fragmentTransaction.replace(android.R.id.content, createWalletFragment)
                .commit();


    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    private void launchHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}