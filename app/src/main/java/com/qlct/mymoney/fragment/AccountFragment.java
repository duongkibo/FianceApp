package com.qlct.mymoney.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.qlct.mymoney.ProfileActivity;
import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.NotificationReceiver;
import com.qlct.mymoney.model.DataBaseIntalizerUser;
import com.qlct.mymoney.model.ExpendituresDB;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;
import com.qlct.mymoney.ui.AddGroupActivity;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private RelativeLayout rlSettings;
    private Animation bottomAnimation;
    private Context context;
    private RelativeLayout rlAccount_profile;
    private TextView userName;
    private View view;
    private FragmentManager fragmentManager;
    private Switch btnSetNotification;
    private TextView tvIncome,tvExpends,tvSums;

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
      // new  GetCount(view).execute(20);

    }
    class GetCount extends  AsyncTask<Integer,String,String>
    {
        View views;
        GetCount(View views)
        {
            this.views = views;
        }
        @Override
        protected String doInBackground(Integer... voids) {
            int a = ExpendituresDB.getExpendituresDB(getContext().getApplicationContext()).getExpendituresDao().rowCount();
            Log.d("valuesxxx",a+"");
            publishProgress(a+"","ss","sss");
            return "finish";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d("resultss",values[0]);
            TextView tv_Expend = (TextView) views.findViewById(R.id.tv_expendituse);
            tv_Expend.setText(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            TextView tv_Expend = (TextView) views.findViewById(R.id.tv_expendituse);
            tv_Expend.setText(s);
        }
    }

    private void iniViews() {
        bottomAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_animation);
        rlSettings = view.findViewById(R.id.rlSettings);
        fragmentManager = getChildFragmentManager();
        openNotification();
        tvExpends = view.findViewById(R.id.tv_expendituse);
        tvIncome = view.findViewById(R.id.tv_inditues);
        tvSums = view.findViewById(R.id.tv_sums);
        userName = view.findViewById(R.id.username);
        rlAccount_profile = view.findViewById(R.id.rlAccount_profile);
        rlAccount_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUserDituresViewModel viewModels = ViewModelProviders.of(AccountFragment.this).get(AddUserDituresViewModel.class);
                viewModels.getUserDitures().observe(getActivity(), new Observer<UserDitures>() {
                    @Override
                    public void onChanged(UserDitures userDitures) {
                        if (userDitures != null) {

                            Intent intent = new Intent(getContext(), ProfileActivity.class);
                            intent.putExtra("xxx",userDitures.getId());
                            Log.d("ssss", userDitures.getId()+"");
                            startActivity(intent);


                        }
                    }
                });

            }
        });

        DataBaseIntalizerUser.populateAsync(UserDituresDB.getUserDituresDB(getContext()));
        AddUserDituresViewModel viewModel = ViewModelProviders.of(this).get(AddUserDituresViewModel.class);

        // viewModel2.getIncome().observe(getActivity(), incomeAdapter::setIncomeDituresList);
        viewModel.getUserDitures().observe(getActivity(), new Observer<UserDitures>() {
            @Override
            public void onChanged(UserDitures userDitures) {
                if (userDitures != null) {

                    userName.setText(userDitures.getUsername());


                }
            }

        });

    }

    private void openNotification() {
        btnSetNotification = view.findViewById(R.id.btnSetNotification);
        btnSetNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, 8);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    Intent notifyIntent = new Intent(getContext(), NotificationReceiver.class);
                    notifyIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);
                } else {

                }
            }
        });
    }


}