package com.qlct.mymoney.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.qlct.mymoney.model.UserDao;
import com.qlct.mymoney.model.UserDitures;
import com.qlct.mymoney.model.UserDituresDB;



public class AddUserDituresViewModel extends AndroidViewModel {

    private UserDituresDB userDituresDB;
    private final LiveData<UserDitures> users;

    public AddUserDituresViewModel(Application application) {
        super(application);
        userDituresDB = UserDituresDB.getUserDituresDB(application);
        users = UserDituresDB.getUserDituresDB(application).getUserDituresDao().getAll();
    }

    public void addUserDituresDB(UserDitures userDitures){
        userDituresDB.getUserDituresDao().insert(userDitures);
    }

    public void upDate(UserDitures userDitures){
        userDituresDB.getUserDituresDao().update(userDitures);
    }

    public LiveData<UserDitures> getUserDitures(){
        return users;
    }


}
