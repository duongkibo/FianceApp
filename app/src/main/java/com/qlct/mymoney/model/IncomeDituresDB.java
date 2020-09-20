package com.qlct.mymoney.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {IncomeDitures.class}, version = 1)
public abstract class IncomeDituresDB extends RoomDatabase {
    public static IncomeDituresDB incomeDitures;

    public static IncomeDituresDB getIncomeDituresBD(Context context) {
        if (incomeDitures == null) {
            incomeDitures = Room.databaseBuilder(context, IncomeDituresDB.class, "incomeDitures_BD").build();

        }
        return incomeDitures;
    }

    public abstract IncomeDituresDao getIncomeDituresDao();

    public void destroyDB() {
        incomeDitures = null;
    }

}
