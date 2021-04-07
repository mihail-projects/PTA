package com.project.pta;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sport {

    public Sport(int sid, String name, String type, String gender){
        this.sid = sid;
        this.name = name;
        this.type = type;
        this.gender = gender;
    }

    @PrimaryKey(autoGenerate = true)
    public int sid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "gender")
    public String gender;

}