package com.qlct.mymoney.model;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Insert;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Expenditures.class},version = 1)
public abstract class ExpendituresDB extends RoomDatabase {
    public  static   ExpendituresDB expenditures;
    public  static  ExpendituresDB getExpendituresDB(Context context)
    {
        if(expenditures == null)
        {
            expenditures = Room.databaseBuilder(context,  ExpendituresDB.class, "expenditures_db").build();

        }
        return expenditures;
    }
    public abstract ExpendituresDao getExpendituresDao();
    public void destroyDB(){
        expenditures = null;
    }

}
