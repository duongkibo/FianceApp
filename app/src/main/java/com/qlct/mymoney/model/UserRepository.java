package com.qlct.mymoney.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<UserDitures> allUser;

    public UserRepository(Application application) {
        UserDituresDB userDituresDB = UserDituresDB.getUserDituresDB(application);
        userDao = userDituresDB.getUserDituresDao();
        allUser = userDao.getAll();
    }

    public void insert(UserDitures userDitures) {

    }

    public void update(UserDitures userDitures) {
        new UpdateAsyncTask(userDao).execute(userDitures);
    }

    public void delete(UserDitures userDitures) {

    }

    public void deleteAll(UserDitures userDitures) {

    }

    public LiveData<UserDitures> getAllUser() {
        return allUser;
    }

    private static class UpdateAsyncTask extends AsyncTask<UserDitures, Void, Void> {
        private UserDao userDao;

        private UpdateAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserDitures... userDitures) {
            userDao.update(userDitures);
            return null;
        }
    }

}

