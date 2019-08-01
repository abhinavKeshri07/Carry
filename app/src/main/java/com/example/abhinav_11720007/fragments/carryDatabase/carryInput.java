package com.example.abhinav_11720007.fragments.carryDatabase;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abhinav_11720007.MainActivity;
import com.example.abhinav_11720007.R;
import com.example.abhinav_11720007.fragments.carry;
import com.example.abhinav_11720007.fragments.carryRecyclerViews.RecyclerViewAdapterInput;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class carryInput extends Fragment {
    public static class mAsyncTask extends AsyncTask<ArrayList<String>,Integer, Integer>{
        Context application;
        String NewGroupName;
        mAsyncTask(Context context, String s){
          application = context;
          NewGroupName = s;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(ArrayList<String>... arrayLists) {
            carryDatabaseHelper cdbh = new carryDatabaseHelper(application);
            if(cdbh.insert(NewGroupName,arrayLists[0])){return -1;}
            return 1;
        }
        @Override
        protected void onPostExecute(Integer integer) {
            if(integer == 1) {
                Toast.makeText(application, "previous operation was successful", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(application, "Something went wrong : not inserted in DB" , Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
    private static final String TAG = "carryInput";
    carryInputViewModel mcarryInputViewModel;

    RecyclerViewAdapterInput adapter;

    Button addBtn, cancelBtn, createBtn;
    EditText itemEditText;
    String NewGroupName;
    MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.input_carry_fragment, container, false);
        itemEditText = v.findViewById(R.id.item_edit_text);
        addBtn = v.findViewById(R.id.add_input);
        cancelBtn = v.findViewById(R.id.cancel_carry_input);
        createBtn = v.findViewById(R.id.save_group);
        mcarryInputViewModel = ViewModelProviders.of(this).get(carryInputViewModel.class);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(itemEditText.getText().toString()).equals("")) {
                    mcarryInputViewModel.add(itemEditText.getText().toString());
                    adapter.notifyItemInserted(mcarryInputViewModel.size() - 1);
                    itemEditText.setText("");
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction frt = null;
                if (getFragmentManager() != null) {
                    frt = getFragmentManager().beginTransaction();
                    frt.replace(R.id.fragmentContainer, new carry());
                    frt.commit();
                }
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new mAsyncTask(getContext().getApplicationContext(), NewGroupName).execute(mcarryInputViewModel.getArrayList());
                FragmentTransaction frt;
                if (getFragmentManager() != null) {
                    frt = getFragmentManager().beginTransaction();
                    carry c = new carry();
                    c.addMainActivity(mainActivity);
                    frt.replace(R.id.fragmentContainer,c );
                    frt.commit();
                }
            }
        });
        initRecyclerView(v);
        return v;
    }
    public void addMainActivity(MainActivity mainActivity){this.mainActivity = mainActivity;}
    public void initRecyclerView(View v) {
        RecyclerView rv = v.findViewById(R.id.group_item_input_recycler_view);
        adapter = new RecyclerViewAdapterInput(mcarryInputViewModel.getArrayList());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "initRecyclerView: no problem");
    }
    public void setNewGroupName(String s)
    {
        this.NewGroupName = s;
    }
}
