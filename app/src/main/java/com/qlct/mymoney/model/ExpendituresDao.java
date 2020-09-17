package com.qlct.mymoney.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpendituresDao {
    @Insert
    void insert(Expenditures... expenditures);
    @Query("SELECT* FROM Expenditures  ")
    List<Expenditures> getAllItems();
}
