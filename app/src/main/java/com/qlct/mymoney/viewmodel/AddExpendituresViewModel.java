package com.qlct.mymoney.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;

import java.util.List;

public class AddExpendituresViewModel extends AndroidViewModel {
    private ExpendituresDB expendituresDB;
    private final LiveData<List<Expenditures>> expandituresList;
    private LiveData<List<Expenditures>> expanddituresDay;
    private LiveData<List<Expenditures>> expanddituresMonths;
    private LiveData<List<Expenditures>> expanddituresYears;

    public AddExpendituresViewModel(Application application)
    {
        super(application);
        expanddituresDay = new MutableLiveData<>();
        expanddituresMonths = new MutableLiveData<>();
        expanddituresYears = new MutableLiveData<>();
        expendituresDB =  ExpendituresDB.getExpendituresDB(application);
        expandituresList = ExpendituresDB.getExpendituresDB(application).getExpendituresDao().getAllItems();
    }
    public  void addExpendituresDB(Expenditures expenditures)
    {
        expendituresDB.getExpendituresDao().insert(expenditures);
    }
    public  LiveData<List<Expenditures>> getExpanddituresDay(int days)
    {
        return expendituresDB.getExpendituresDao().getItemDays(days) ;
    }
    public  LiveData<List<Expenditures>> getExpanddituresMonths(int month)
    {
        return expendituresDB.getExpendituresDao().getItemMonths(month) ;
    }
    public  LiveData<List<Expenditures>> getExpanddituresYears(int years)
    {
        return expendituresDB.getExpendituresDao().getItemYears(years) ;
    }


    public  LiveData<List<Expenditures>> getExpendiures()
    {
        return expandituresList;
    }
}
