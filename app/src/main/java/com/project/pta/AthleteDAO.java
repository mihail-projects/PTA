package com.project.pta;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AthleteDAO {

    @Query("SELECT * FROM athlete")
    List<Athlete> getAll();

    @Query("SELECT * FROM athlete WHERE aid IN (:athleteIds)")
    List<Athlete> loadAllByIds(int[] athleteIds);

    @Insert
    void insert(Athlete athlete);

    @Update
    void update(Athlete athlete);

    @Delete
    void delete(Athlete athlete);

}