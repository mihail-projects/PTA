package com.project.pta;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeamDAO {

    @Query("SELECT * FROM team")
    List<Team> getAll();

    @Query("SELECT * FROM team WHERE tid IN (:teamIds)")
    List<Team> loadAllByIds(int[] teamIds);

    @Insert
    void insert(Team team);

    @Update
    void update(Team team);

    @Delete
    void delete(Team athlete);

}