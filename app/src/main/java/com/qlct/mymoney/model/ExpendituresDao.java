package com.qlct.mymoney.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpendituresDao {
    @Insert
    void insert(Expenditures... expenditures);

    @Update
    void update(Expenditures... expenditures);

    @Delete
    void delete(Expenditures... expenditures);

    @Query("SELECT COUNT(*) FROM Expenditures")
    int rowCount();

    @Query("SELECT* FROM Expenditures")
    LiveData<List<Expenditures>> getAllItems();

    @Query("SELECT * FROM expenditures WHERE day=:days AND month=:month ")
    LiveData<List<Expenditures>> getItemDays(int days,int month);



    @Query("SELECT * FROM expenditures WHERE month=:months ")
    LiveData<List<Expenditures>> getItemMonths(int months);

    @Query("SELECT * FROM expenditures WHERE year=:years ")
    LiveData<List<Expenditures>> getItemYears(int years);

}
