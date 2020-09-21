package com.qlct.mymoney.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserDitures... userDitures);

    @Update
    void update(UserDitures... userDitures);

    @Delete
    void delete(UserDitures... userDitures);

    @Query("SELECT COUNT(*) FROM UserDitures")
    int rowCount();

    @Query("SELECT * FROM UserDitures")
    LiveData<List<UserDitures>> getAll();
}
