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

    public static UserDituresDB getUserDituresDB(Context context) {
        if (userDitures == null) {
            userDitures = Room.databaseBuilder(context, UserDituresDB.class, "userditures_db").build();
        }
        return userDitures;
    }


    public abstract UserDao getUserDituresDao();

    public void DetroyDB() {
        userDitures = null;
    }

}
