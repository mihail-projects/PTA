package com.project.pta;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SportDAO {

    @Query("SELECT * FROM sport")
    List<Sport> getAll();

    @Query("SELECT * FROM sport WHERE sid IN (:sportIds)")
    List<Sport> loadAllByIds(int[] sportIds);

    @Insert
    void insert(Sport sport);

    @Update
    void update(Sport sport);

    @Delete
    void delete(Sport sport);

}