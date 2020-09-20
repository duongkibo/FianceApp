package com.qlct.mymoney.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IncomeDituresDao {
    @Insert
    void insert(IncomeDitures... incomeDitures);

    @Update
    void update(IncomeDitures... incomeDitures);

    @Delete
    void delete(IncomeDitures... incomeDitures);

    @Query("SELECT COUNT(*) FROM IncomeDitures")
    int rowCount();

    @Query("SELECT* FROM IncomeDitures  ")
    LiveData<List<IncomeDitures>> getAllItems();
}

