package com.qlct.mymoney.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;

import java.util.List;

public class AddExpendituresViewModel extends AndroidViewModel {
    private ExpendituresDB expendituresDB;
    private final LiveData<List<Expenditures>> expandituresList;

    public AddExpendituresViewModel(Application application)
    {
        super(application);
        expendituresDB =  ExpendituresDB.getExpendituresDB(application);
        expandituresList = ExpendituresDB.getExpendituresDB(application).getExpendituresDao().getAllItems();
    }
    public  void addExpendituresDB(Expenditures expenditures)
    {
        expendituresDB.getExpendituresDao().insert(expenditures);
    }
    public  LiveData<List<Expenditures>> getExpendiures()
    {
        return expandituresList;
    }
}
