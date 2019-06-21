package com.example.abhinav_11720007.fragments.carryDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Group.class}, version = 1)
public abstract class GroupDatabase extends RoomDatabase {
    private static GroupDatabase instance;
    public abstract GroupDao groupDao();
    public static synchronized GroupDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),GroupDatabase.class,"group_database").fallbackToDestructiveMigration().build();
        }
        return instance;


    }
}
