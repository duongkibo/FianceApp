package com.qlct.mymoney.model;

import android.os.AsyncTask;

import com.qlct.mymoney.R;

import java.util.ArrayList;
import java.util.List;

public class DataBaseIntalizerUser {
    public static void populateAsync(final UserDituresDB userDituresDB) {
        new PopulateDbasync(userDituresDB).execute();
    }

    protected static class PopulateDbasync extends AsyncTask<Void, Void, Void> {
        private final UserDituresDB userDituresDB;

        PopulateDbasync(UserDituresDB userDituresDB) {
            this.userDituresDB = userDituresDB;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            if (userDituresDB.getUserDituresDao().rowCount() == 0){
                List<UserDitures> userDitures = new ArrayList<>();
                userDitures.add(new UserDitures("Toan", 1666666, "123456"));
            }
            return null;
        }
    }

}
