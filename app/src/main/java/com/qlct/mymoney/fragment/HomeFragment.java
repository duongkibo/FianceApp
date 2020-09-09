package com.qlct.mymoney.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qlct.mymoney.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private Toolbar toolbar;
    Fragment homeDay = new HomeDayFragment();


    RelativeLayout homeFragment;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Total: 2,000,000$");
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
                }
                return true;
            }
        });

    }

    private void homeDayScreen() {

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container2, homeDay).commit();

    }

    private void homeYearScreen() {
        Fragment newFragment = new HomeDayFragment();
        FragmentActivity fragmentActivity = new FragmentActivity();
        fragmentManager = fragmentActivity.getSupportFragmentManager();
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.homwDayScreen, new HomeYearFragment(), "NewFragmentTag");
        ft.addToBackStack(null);
        ft.commit();
    }

    private void homeMonthScreen() {
        Fragment newFragment = new HomeDayFragment();
        FragmentActivity fragmentActivity = new FragmentActivity();
        fragmentManager = fragmentActivity.getSupportFragmentManager();
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.homwDayScreen, new HomeMonthFragment(), "NewFragmentTag");
        ft.addToBackStack(null);
        ft.commit();
    }

//    private void homeYearScreen() {
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container2, homeYear).commit();
//
//    }

//    public void clearStack() {
//        /*int backStackEntry = getActivity().getSupportFragmentManager().getBackStackEntryCount();
//        if (backStackEntry > 0) {
//            for (int i = 0; i < backStackEntry; i++) {
//                getActivity().getSupportFragmentManager().popBackStackImmediate();
//            }
//        }
//
//        if (getActivity().getSupportFragmentManager().getFragments() != null && getActivity().getSupportFragmentManager().getFragments().size() > 0) {
//            for (int i = 0; i < getActivity().getSupportFragmentManager().getFragments().size(); i++) {
//                Fragment mFragment = getActivity().getSupportFragmentManager().getFragments().get(i);
//                if (mFragment != null) {
//                    getActivity().getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
//                }
//            }
//        }*/
//
//        FragmentManager manager = getActivity().getSupportFragmentManager();
//        FragmentTransaction trans = manager.beginTransaction();
//        trans.remove(homeDay);
//        trans.commit();
//        manager.popBackStack();
//
//    }


}
