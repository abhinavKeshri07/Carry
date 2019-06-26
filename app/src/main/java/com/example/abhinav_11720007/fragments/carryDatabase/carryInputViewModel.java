package com.example.abhinav_11720007.fragments.carryDatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;

public class carryInputViewModel extends AndroidViewModel {
    private ArrayList<String> mArrayList;
    public carryInputViewModel(@NonNull Application application) {
        super(application);
        mArrayList = new ArrayList<>();
    }
    public void add(String s){mArrayList.add(s);}
    public int size(){return mArrayList.size();}
    public ArrayList<String> getArrayList(){return this.mArrayList;}
}
