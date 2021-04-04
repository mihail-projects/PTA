package com.project.pta;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Team {

    public Team(String name, String stadium, String city, String country, int sportCode, int foundingYear){
        this.name = name;
        this.stadium = stadium;
        this.city = city;
        this.country = country;
        this.sportCode = sportCode;
        this.foundingYear = foundingYear;
    }

    @PrimaryKey(autoGenerate = true)
    public int tid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "stadium")
    public String stadium;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "sportCode")
    public int sportCode;

    @ColumnInfo(name = "foundingYear")
    public int foundingYear;

}