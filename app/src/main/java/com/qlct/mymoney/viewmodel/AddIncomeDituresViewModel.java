package com.qlct.mymoney.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.model.IncomeDituresDB;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;

import java.util.List;

public class AddIncomeDituresViewModel extends AndroidViewModel {
    private IncomeDituresDB incomeDituresDB;
    private final LiveData<List<IncomeDitures>> income;
    private LiveData<List<IncomeDitures>> incomeDay;
    private LiveData<List<IncomeDitures>> incomeMonths;
    private LiveData<List<IncomeDitures>> incomeYears;

    public AddIncomeDituresViewModel(Application application) {
        super(application);
        incomeDay = new MutableLiveData<>();
        incomeMonths = new MutableLiveData<>();
        incomeYears = new MutableLiveData<>();
        incomeDituresDB = IncomeDituresDB.getIncomeDituresBD(application);
        income = IncomeDituresDB.getIncomeDituresBD(application).getIncomeDituresDao().getAllItems();
    }
    public  void addIncomeDituresDB(IncomeDitures incomeDitures)
    {
        incomeDituresDB.getIncomeDituresDao().insert(incomeDitures);
    }
    public LiveData<List<IncomeDitures>> getIncomeDituresDay(int days) {
        return incomeDituresDB.getIncomeDituresDao().getItemDays(days);
    }

    public LiveData<List<IncomeDitures>> getIncomeDituresMonths(int month) {
        return incomeDituresDB.getIncomeDituresDao().getItemMonths(month);
    }

    public LiveData<List<IncomeDitures>> getIncomeDituresYears(int years) {
        return incomeDituresDB.getIncomeDituresDao().getItemYears(years);
    }
    public  LiveData<List<IncomeDitures>> getIncome()
    {
        return income;
    }

}

