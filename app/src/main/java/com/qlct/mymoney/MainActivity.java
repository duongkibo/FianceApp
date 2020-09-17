package com.qlct.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.qlct.mymoney.fragment.AccountFragment;
import com.qlct.mymoney.fragment.CreateFianceFragment;
import com.qlct.mymoney.fragment.ReportFragment;
import com.qlct.mymoney.fragment.HomeFragment;
import com.qlct.mymoney.fragment.PlanFragment;

import io.alterac.blurkit.BlurLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private CreateFianceFragment createFianceFragment;
    private ConstraintLayout createFianceScreen;

    Animation topAnimation;
    Animation bottomAnimation;
    private Toolbar toolbar;
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    FloatingActionButton btnAdd;
    MenuItem menuItem;
    BlurLayout blurLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setBottomNavigationView();
        TestRealm.Setup(this.getApplicationContext());
    }

    private void initView() {
        createFianceScreen = findViewById(R.id.createFianceScreen);
        bottomNav = findViewById(R.id.bottom_nav);
        btnAdd = findViewById(R.id.btn_add);
      //  blurLayout = findViewById(R.id.blurLayout);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        btnAdd.setOnClickListener(this);


    }

    private void setBottomNavigationView() {

        bottomNav.setItemSelected(R.id.home, true);
        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();


        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.discover:
                        fragment = new ReportFragment();
                        break;
                    case R.id.plan:
                        fragment = new PlanFragment();
                        break;
                    case R.id.account:
                        fragment = new AccountFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Error in creating fragment");
                }
            }
        });

    }

    private void openCreateFianceFragment() {

        createFianceFragment = new CreateFianceFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.bottom_animation, R.anim.bottom_animation);
        fragmentTransaction.replace(android.R.id.content, createFianceFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                openCreateFianceFragment();
                /*blurLayout.startBlur();
                blurLayout.lockView();*/
                break;

        }
    }

}