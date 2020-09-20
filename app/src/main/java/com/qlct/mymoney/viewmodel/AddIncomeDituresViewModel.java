package com.qlct.mymoney.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.IncomeDitures;
import com.qlct.mymoney.model.IncomeDituresDB;

import java.util.List;

public class AddIncomeDituresViewModel extends AndroidViewModel {
    private IncomeDituresDB incomeDituresDB;
    private final LiveData<List<IncomeDitures>> income;

    public AddIncomeDituresViewModel(Application application) {
        super(application);
        incomeDituresDB = IncomeDituresDB.getIncomeDituresBD(application);
        income = IncomeDituresDB.getIncomeDituresBD(application).getIncomeDituresDao().getAllItems();
    }
    public  void addIncomeDituresDB(IncomeDitures incomeDitures)
    {
        incomeDituresDB.getIncomeDituresDao().insert(incomeDitures);
    }
    public  LiveData<List<IncomeDitures>> getIncome()
    {
        return income;
    }
}
