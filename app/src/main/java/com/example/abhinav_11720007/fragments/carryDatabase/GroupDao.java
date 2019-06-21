package com.example.abhinav_11720007.fragments.carryDatabase;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GroupDao {

    @Insert
    void insert(Group group);

    @Update
    void update(Group group);

    @Delete
    void delete(Group group);

    @Query("Delete from Group_table")
    void deleteAllGroups();

    @Query("SELECT * FROM `Group_table` ORDER BY title DESC")
    LiveData<List<Group>> getAllGroups();
}
