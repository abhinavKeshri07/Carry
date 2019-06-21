package com.example.abhinav_11720007.fragments.carryDatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Group_table")
public class Group {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String items;

    public Group(String title, String items) {
        this.title = title;
        this.items = items;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public String getItems() {
        return items;
    }

}