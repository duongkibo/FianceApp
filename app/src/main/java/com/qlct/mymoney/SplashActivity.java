package com.qlct.mymoney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.qlct.mymoney.model.DataBaseIntalizerUser;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation topAnimation;
    Animation bottomAnimation;

    ImageView img_splash;
    TextView txt_splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initview();


    }

    private void initview() {
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        img_splash = findViewById(R.id.img_splash);
        txt_splash = findViewById(R.id.txt_splash);

        img_splash.setAnimation(topAnimation);
        txt_splash.setAnimation(bottomAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DataBaseIntalizerUser.populateAsync(UserDituresDB.getUserDituresDB(getApplicationContext()));
                AddUserDituresViewModel viewModel = ViewModelProviders.of(SplashActivity.this).get(AddUserDituresViewModel.class);
                viewModel.getUserDitures().observe(SplashActivity.this, new Observer<UserDitures>() {
                    @Override
                    public void onChanged(UserDitures userDitures) {
                        if (userDitures == null) {
                            Intent intent = new Intent(SplashActivity.this, ViewsSildeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(SplashActivity.this, PinPasswordActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                });


            }
        }, SPLASH_SCREEN);

    }

}
