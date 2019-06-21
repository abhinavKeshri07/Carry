package com.example.abhinav_11720007.fragments.carryDatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class GroupRepository {
    private GroupDao groupDao;
    private LiveData<List<Group>> allGroups;

    public GroupRepository(Application application) {
        GroupDatabase database = GroupDatabase.getInstance(application);
        groupDao = database.groupDao();
        allGroups = groupDao.getAllGroups();
    }

    public void insert(Group group) {
        new InsertGroupAsyncTask(groupDao).execute(group);
    }

    public void update(Group group) {
        new UpdateGroupAsyncTask(groupDao).execute(group);
    }

    public void delete(Group group) {
        new DeleteGroupAsyncTask(groupDao).execute(group);
    }

    public void deleteAllGroups() {
        new DeleteAllGroupsAsyncTask(groupDao).execute();
    }

    public LiveData<List<Group>> getAllGroups() {
        return allGroups;
    }

    private static class InsertGroupAsyncTask extends AsyncTask<Group, Void, Void> {
        private GroupDao groupDao;

        private InsertGroupAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(Group... groups) {
            groupDao.insert(groups[0]);
            return null;
        }
    }

    private static class UpdateGroupAsyncTask extends AsyncTask<Group, Void, Void> {
        private GroupDao groupDao;

        private UpdateGroupAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(Group... groups) {
            groupDao.update(groups[0]);
            return null;
        }
    }

    private static class DeleteGroupAsyncTask extends AsyncTask<Group, Void, Void> {
        private GroupDao groupDao;

        private DeleteGroupAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(Group... groups) {
            groupDao.delete(groups[0]);
            return null;
        }
    }

    private static class DeleteAllGroupsAsyncTask extends AsyncTask<Void, Void, Void> {
        private GroupDao groupDao;

        private DeleteAllGroupsAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            groupDao.deleteAllGroups();
            return null;
        }
    }
}