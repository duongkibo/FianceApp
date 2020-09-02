package com.qlct.mymoney.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.qlct.mymoney.MainActivity;
import com.qlct.mymoney.R;

import java.util.Calendar;

public class IncomActivity extends AppCompatActivity {
    private Toolbar toolbarTransactionIncome;
    private CardView cardViewCalendarIncome;
    private EditText edtCalendarIncome;
    private DatePickerDialog.OnDateSetListener setListener;
    private TextView txtExpense;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        iniViews();
        actionToolbar();
    }

    private void iniViews() {
        toolbarTransactionIncome = findViewById(R.id.toolbarTransactionIncome);
        cardViewCalendarIncome = findViewById(R.id.cardviewCalendarIncome);
        edtCalendarIncome = findViewById(R.id.edtCalendarIncome);
        txtExpense = findViewById(R.id.txtExpense);
        txtExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IncomActivity.this, CreateFianceActivity.class);
                startActivity(intent);
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edtCalendarIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(IncomActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edtCalendarIncome.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }


    private void actionToolbar() {
        setSupportActionBar(toolbarTransactionIncome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTransactionIncome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
