package com.example.abhinav_11720007.fragments.carryDatabase;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class GroupViewModel extends AndroidViewModel {
    private GroupRepository repository;
    private LiveData<List<Group>> allGroups;

    public GroupViewModel(@NonNull Application application) {
        super(application);
        repository = new GroupRepository(application);
        allGroups = repository.getAllGroups();
    }

    public void insert(Group group) {
        repository.insert(group);
    }

    public void update(Group group) {
        repository.update(group);
    }

    public void delete(Group group) {
        repository.delete(group);
    }

    public void deleteAllGroups() {
        repository.deleteAllGroups();
    }

    public LiveData<List<Group>> getAllGroups() {
        return allGroups;
    }
}
