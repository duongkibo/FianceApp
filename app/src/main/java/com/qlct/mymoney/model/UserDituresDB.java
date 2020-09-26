package com.qlct.mymoney.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.qlct.mymoney.viewmodel.AddUserDituresViewModel;

@Database(entities = {UserDitures.class}, version = 1)
public abstract class UserDituresDB extends RoomDatabase {
    public static UserDituresDB userDitures;
    private UserDao userDao;

    public static UserDituresDB getUserDituresDB(Context context) {
        if (userDitures == null) {
            userDitures = Room.databaseBuilder(context, UserDituresDB.class, "userditures_db").build();
        }
        return userDitures;
    }

    public void updateUserDituresDB(UserDitures userDitures){
        new UpdateAsyncTask(userDao).execute(userDitures);
    }

    public abstract UserDao getUserDituresDao();

    public void DetroyDB() {
        userDitures = null;
    }

    private static class UpdateAsyncTask extends AsyncTask<UserDitures, Void, Void> {
        private UserDao userDao;

        private UpdateAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserDitures... userDitures) {
            userDao.update(userDitures);
            return null;
        }
    }
}
