package com.qlct.mymoney.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.NotificationReceiver;
import com.qlct.mymoney.ui.AddGroupActivity;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private RelativeLayout rlSettings;
    private Animation bottomAnimation;
    private Context context;
    private View view;
    private FragmentManager fragmentManager;
    private Switch btnSetNotification;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniViews();
    }

    private void iniViews() {
        bottomAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_animation);
        rlSettings = view.findViewById(R.id.rlSettings);
        fragmentManager = getChildFragmentManager();
        openNotification();

    }

    private void openNotification() {
        btnSetNotification = view.findViewById(R.id.btnSetNotification);
        btnSetNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, 9);
                    calendar.set(Calendar.MINUTE, 50);
                    calendar.set(Calendar.SECOND, 0);
                    Intent notifyIntent = new Intent(getContext(), NotificationReceiver.class);
                    notifyIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),0,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(),1000 * 60 * 60 * 24, pendingIntent);
                }else {

                }
            }
        });
    }


}
