package com.example.abhinav_11720007.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.abhinav_11720007.MainActivity;
import com.example.abhinav_11720007.R;
import com.example.abhinav_11720007.fragments.carryDatabase.carryDatabaseHelper;
import com.example.abhinav_11720007.fragments.carryDatabase.carryInput;
import com.example.abhinav_11720007.fragments.carryOthers.GroupNameDialogue;
import com.example.abhinav_11720007.fragments.carryRecyclerViews.RecyclerViewAdapterInput;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class carry extends Fragment {
    public static class mAsyncTask2 extends AsyncTask<Void,Integer, ArrayList<String>> {
        Context application;
        WeakReference<carry> mWeakReference;
        ArrayList<String> allGroups = new ArrayList<>(10);
        mAsyncTask2(Context context, carry c){
            application = context;
            mWeakReference = new WeakReference<>(c);
        }
        @Override
        protected ArrayList<String> doInBackground(Void... v) {
            carryDatabaseHelper cdbh = new carryDatabaseHelper(application);
            Cursor c = cdbh.getGroupNames();
            while(c.moveToNext())
            {
                allGroups.add(c.getString(0));
            }
            return allGroups;
        }

        @Override
        protected void onPostExecute(ArrayList<String> arrayList) {
            super.onPostExecute(arrayList);
            if(mWeakReference.get()!= null)
            {
                carry c = mWeakReference.get();
                c.adapter.notifyDataSetChanged();
            }
        }
    }
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private carryDatabaseHelper cdbh;

    private Button create_group_btn;


    private String newGroupName;
    private MainActivity mainActivity;
    private ArrayList<String> allGroups = new ArrayList<>();
    private RecyclerView rv ;
    RecyclerViewAdapterInput adapter;
    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.carry_fragment, container, false);
        create_group_btn = v.findViewById(R.id.createGroup);
        create_group_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GroupNameDialogue gnd = new GroupNameDialogue();
                gnd.show(getFragmentManager(),"Group name");
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                carryInput ci = new carryInput();
                ci.addMainActivity(mainActivity);
                ci.setNewGroupName(newGroupName);
                frt.replace(R.id.fragmentContainer,ci);
                frt.commit();

            }
        });
        mAsyncTask2 mat = new carry.mAsyncTask2(getContext().getApplicationContext(), this);
        mat.execute();

        //init recyclerview
        rv = v.findViewById(R.id.carryRecyclerView);
        adapter = new RecyclerViewAdapterInput(allGroups);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    public void addMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.mainActivity.setcarry(this);
    }

    public void setNewGroupName(String s){
         this.newGroupName = s;
    }

}
