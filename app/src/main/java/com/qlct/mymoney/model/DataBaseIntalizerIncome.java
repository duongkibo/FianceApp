package com.qlct.mymoney.model;

import android.os.AsyncTask;

import com.qlct.mymoney.R;

import java.util.ArrayList;
import java.util.List;

public class DataBaseIntalizerIncome {
    public static void populateAsync(final IncomeDituresDB incomeDituresDB) {
        new PopulateDbasync(incomeDituresDB).execute();
    }

    protected static class PopulateDbasync extends AsyncTask<Void, Void, Void> {
        private final IncomeDituresDB incomeDituresDB;

        PopulateDbasync(IncomeDituresDB incomeDituresDB) {
            this.incomeDituresDB = incomeDituresDB;
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            if (incomeDituresDB.getIncomeDituresDao().rowCount() == 0) {
                List<IncomeDitures> incomeDitures = new ArrayList<>();
                incomeDitures.add(new IncomeDitures("1000", "xxx", R.drawable.bills, "bill", 12, 3, 2012));
                incomeDitures.add(new IncomeDitures("1000", "xxx", R.drawable.bills, "bill", 12, 3, 2012));
                incomeDitures.add(new IncomeDitures("1000", "xxx", R.drawable.bills, "bill", 12, 3, 2012));
                incomeDitures.add(new IncomeDitures("1000", "xxx", R.drawable.bills, "bill", 12, 3, 2012));
                incomeDitures.add(new IncomeDitures("1000", "xxx", R.drawable.bills, "bill", 12, 3, 2012));
                incomeDitures.add(new IncomeDitures("1000", "xxx", R.drawable.bills, "bill", 12, 3, 2012));
                incomeDitures.add(new IncomeDitures("1000", "xxx", R.drawable.bills, "bill", 12, 3, 2012));
            }
            return null;
        }
    }
}
