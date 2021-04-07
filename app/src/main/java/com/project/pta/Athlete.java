package com.project.pta;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Athlete {

    public Athlete(int aid, String name, String lastname, String city, String country, int sportCode, int birthYear){
        this.aid = aid;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.country = country;
        this.sportCode = sportCode;
        this.birthYear = birthYear;
    }

    @PrimaryKey(autoGenerate = true)
    public int aid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "lastname")
    public String lastname;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "sportCode")
    public int sportCode;

    @ColumnInfo(name = "birthYear")
    public int birthYear;

}