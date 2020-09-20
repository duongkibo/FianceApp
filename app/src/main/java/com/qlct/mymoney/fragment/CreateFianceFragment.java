package com.qlct.mymoney.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.qlct.mymoney.R;

public class CreateFianceFragment extends Fragment {

    private View view;
    private ImageView imgClose;
    private ChipNavigationBar topNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_fiance, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        setTopNavigation();
    }

    private void initView() {
        topNav = view.findViewById(R.id.top_nav);
        imgClose = view.findViewById(R.id.imgClose);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

}

    private void setTopNavigation() {

        topNav.setItemSelected(R.id.expense, true);
        Fragment expense = new ExpenseFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container1, expense).commit();

        topNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.expense:
                        fragment = new ExpenseFragment();
                        break;
                    case R.id.income:
                        fragment = new IncomeFragment();
                        break;
                }

                if (fragment != null) {
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container1, fragment).commit();

                } else {
                }
            }
        });
    }

}
