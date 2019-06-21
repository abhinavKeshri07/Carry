package com.example.abhinav_11720007.fragments.carryDatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "group_table")
public class Group {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String GroupName;
    private String items;

    public Group( String groupName, String items) {
        GroupName = groupName;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public String getItems() {
        return items;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public void setItems(String items) {
        this.items = items;
    }
}