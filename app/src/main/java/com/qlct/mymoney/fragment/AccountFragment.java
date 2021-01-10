package com.qlct.mymoney.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.qlct.mymoney.PinPasswordActivity;
import com.qlct.mymoney.ProfileActivity;
import com.qlct.mymoney.R;
import com.qlct.mymoney.adapter.NotificationReceiver;
import com.qlct.mymoney.authenticate.LoginActivity;
import com.qlct.mymoney.dialog.DialogOnclickListener;
import com.qlct.mymoney.dialog.SelectionDialog;
import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.model.IncomeDituresDB;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.utils.SharedPreferencesConstants;
import com.qlct.mymoney.utils.SharedPreferencesManager;
import com.qlct.mymoney.viewmodel.AddExpendituresViewModel;
import com.qlct.mymoney.viewmodel.AddIncomeDituresViewModel;
import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

import java.util.Calendar;
import java.util.List;


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
    public Switch btnSetFinger;
    private TextView tvIncome, tvExpends, tvSums;
    private ConstraintLayout clLogout;

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
        checkFinger();
        tvExpends = view.findViewById(R.id.tv_sum_expen);
        tvIncome = view.findViewById(R.id.tv_sum_indi);

        new GetCount(view).execute();
        new GetCountInti(view).execute();
        new GetCountSum(view).execute();
        AddExpendituresViewModel viewModel = ViewModelProviders.of(this).get(AddExpendituresViewModel.class);
        viewModel.getExpendiures().observe(getActivity(), new Observer<List<Expenditures>>() {
            @Override
            public void onChanged(List<Expenditures> expenditures) {
                if (expenditures != null) {
                    int sum = 0;
                    for (int i = 0; i < expenditures.size(); i++) {
                        sum += Integer.valueOf(expenditures.get(i).getMoney());
                    }
                    tvExpends.setText(sum + "");


                }
            }
        });
        AddIncomeDituresViewModel viewModel1 = ViewModelProviders.of(this).get(AddIncomeDituresViewModel.class);
        viewModel1.getIncome().observe(getActivity(), new Observer<List<IncomeDitures>>() {
            @Override
            public void onChanged(List<IncomeDitures> incomeDitures) {
                if (incomeDitures != null) {
                    int sum = 0;
                    for (int i = 0; i < incomeDitures.size(); i++) {
                        sum += Integer.valueOf(incomeDitures.get(i).getMoney());
                    }
                    tvIncome.setText(sum + "");

                }
            }
        });

    }

    class GetCount extends AsyncTask<Void, String, String> {
        View views;
        String sum = "";

        GetCount(View views) {
            this.views = views;
        }

        @Override
        protected String doInBackground(Void... voids) {
            int a = ExpendituresDB.getExpendituresDB(getContext().getApplicationContext()).getExpendituresDao().rowCount();
            Log.d("valuesxxx", a + "");
            publishProgress(a + "", "ss", "sss");
            return "finish";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d("resultss", values[0]);
            TextView tv_Expend = (TextView) views.findViewById(R.id.tv_expendituse);
            tv_Expend.setText(values[0]);

            sum += values[0];
        }

        @Override
        protected void onPostExecute(String s) {
            TextView tv_Expend = (TextView) views.findViewById(R.id.tv_expendituse);
            tv_Expend.setText(sum);

        }
    }

    class GetCountInti extends AsyncTask<Void, String, String> {
        View views;
        String sum = "";

        GetCountInti(View views) {
            this.views = views;
        }

        @Override
        protected String doInBackground(Void... voids) {
            int a = IncomeDituresDB.getIncomeDituresBD(getContext().getApplicationContext()).getIncomeDituresDao().rowCount();
            Log.d("valuesxxx", a + "");
            publishProgress(a + "", "ss", "sss");
            return "finish";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d("resultss", values[0]);
            TextView tv_Expend = (TextView) views.findViewById(R.id.tv_inditues);
            tv_Expend.setText(values[0]);

            sum += values[0];
        }

        @Override
        protected void onPostExecute(String s) {
            TextView tv_Expend = (TextView) views.findViewById(R.id.tv_inditues);
            tv_Expend.setText(sum);

        }
    }

    class GetCountSum extends AsyncTask<Void, String, String> {
        View views;
        String sum = "";

        GetCountSum(View views) {
            this.views = views;
        }

        @Override
        protected String doInBackground(Void... voids) {
            int a = IncomeDituresDB.getIncomeDituresBD(views.getContext().getApplicationContext()).getIncomeDituresDao().rowCount();
            int b = ExpendituresDB.getExpendituresDB(views.getContext().getApplicationContext()).getExpendituresDao().rowCount();
            int sums = a + b;
            Log.d("valuesxxx", a + "");
            publishProgress(sums + "", "ss", "sss");
            return "finish";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d("resultss", values[0]);
            TextView tv_Expend = (TextView) views.findViewById(R.id.tv_sums);
            tv_Expend.setText(values[0]);

            sum += values[0];
        }

        @Override
        protected void onPostExecute(String s) {
            TextView tv_Expend = (TextView) views.findViewById(R.id.tv_sums);
            tv_Expend.setText(sum);

        }
    }

    private void iniViews() {
        bottomAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_animation);
        rlSettings = view.findViewById(R.id.rlSettings);
        fragmentManager = getChildFragmentManager();
        openNotification();
        tvExpends = view.findViewById(R.id.tv_expendituse);
        btnSetFinger = (Switch) view.findViewById(R.id.btnSetFinger);
        tvIncome = view.findViewById(R.id.tv_inditues);
        tvSums = view.findViewById(R.id.tv_sums);
        userName = view.findViewById(R.id.username);
        rlAccount_profile = view.findViewById(R.id.rlAccount_profile);
        clLogout = view.findViewById(R.id.clLogout);
        clLogout.setOnClickListener(v -> {
            SelectionDialog selectionDialog = new SelectionDialog(getContext(),
                    getContext().getString(R.string.logout),
                    getContext().getString(R.string.logout_confirm),
                    getContext().getString(R.string.cancel),
                    getContext().getString(R.string.ok),
                    new DialogOnclickListener() {
                        @Override
                        public void onButtonEndClick() {
                            SharedPreferencesManager.getDefault().removeKeyPreference(SharedPreferencesConstants.KEY_TOKEN_LOGIN);
                            getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                        }
                    });
            selectionDialog.show();
        });
        rlAccount_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUserDituresViewModel viewModels = ViewModelProviders.of(AccountFragment.this).get(AddUserDituresViewModel.class);
                viewModels.getUserDitures().observe(getActivity(), new Observer<UserDitures>() {
                    @Override
                    public void onChanged(UserDitures userDitures) {
                        if (userDitures != null) {

                            Intent intent = new Intent(getContext(), ProfileActivity.class);
                            intent.putExtra("xxx", userDitures.getId());
                            Log.d("ssss", userDitures.getId() + "");
                            startActivity(intent);


                        }
                    }
                });

            }
        });


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

    private void checkFinger() {
        btnSetFinger.setChecked(update("finger"));

        btnSetFinger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveSwitch("finger", isChecked);
            }
        });


    }

    public void saveSwitch(String key, boolean value) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private boolean update(String key) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public void ff(){
        Toast.makeText(getContext(), "dđ",Toast.LENGTH_SHORT).show();
    }
}