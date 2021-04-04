package com.project.pta;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Sport.class, Team.class, Athlete.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SportDAO sportDAO();
    public abstract TeamDAO teamDAO();
    public abstract AthleteDAO athleteDAO();
}