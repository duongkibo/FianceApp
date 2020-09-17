package com.qlct.mymoney.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.qlct.mymoney.model.Expenditures;
import com.qlct.mymoney.model.ExpendituresDB;

import java.util.List;

public class AddExpendituresViewModel extends AndroidViewModel {
    private ExpendituresDB expendituresDB;
    private LiveData<List<Expenditures>> expandituresList;

    public AddExpendituresViewModel(Application application)
    {
        super(application);
        expendituresDB =  ExpendituresDB.getExpendituresDB(application);
        expandituresList = (LiveData<List<Expenditures>>) ExpendituresDB.getExpendituresDB(getApplication()).getExpendituresDao().getAllItems();
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
