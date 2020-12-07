package com.qlct.mymoney.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qlct.mymoney.R;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

import java.text.DecimalFormat;

public class  HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private String wallet;
    private Toolbar toolbar;
    Fragment homeDay = new HomeDayFragment();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        AddUserDituresViewModel viewModel = ViewModelProviders.of(this).get(AddUserDituresViewModel.class);
        // viewModel2.getIncome().observe(getActivity(), incomeAdapter::setIncomeDituresList);
        viewModel.getUserDitures().observe(getActivity(), new Observer<UserDitures>() {
            @Override
            public void onChanged(UserDitures userDitures) {
                if (userDitures != null) {
                    CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsing_toolbar);
                    collapsingToolbar.setTitle("Ví: " + String.valueOf(decimalFormat.format(userDitures.getWallet())) + " ₫");
                }
            }

        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniViews();
        homeDayScreen();


    }


    private void iniViews() {
        toolbar = view.findViewById(R.id.mToolbar);
        toolbar.inflateMenu(R.menu.menu_home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.open_year:
                        homeYearScreen();
                        break;

                    case R.id.open_month:
                        homeMonthScreen();
                        break;

                    case R.id.open_day:
                        homeDayScreen();
                        break;
                }
                return true;
            }
        });

    }

    private void homeDayScreen() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container2, homeDay)
                .addToBackStack(null).commit();

    }

    private void homeYearScreen() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container2, new HomeYearFragment())
                .addToBackStack(null).commit();
    }

    private void homeMonthScreen() {
        /*Fragment newFragment = new HomeDayFragment();
        FragmentActivity fragmentActivity = new FragmentActivity();
        fragmentManager = fragmentActivity.getSupportFragmentManager();
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container2, new HomeMonthFragment(), "NewFragmentTag");
        ft.addToBackStack(null);
        ft.commit();*/

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container2, new HomeMonthFragment())
                .addToBackStack(null).commit();
    }


}
