package com.example.abhinav_11720007.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.abhinav_11720007.R;
import com.example.abhinav_11720007.fragments.carryDatabase.Group;
import com.example.abhinav_11720007.fragments.carryDatabase.GroupViewModel;

import java.util.List;

public class carry extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private GroupViewModel groupViewModel;

    Button addGroupBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView =  inflater.inflate(R.layout.carry_fragment, container, false);
        addGroupBtn = mainView.findViewById(R.id.carryAddGroupBtn);
        addGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"wow",Toast.LENGTH_SHORT).show();
            }
        });
        groupViewModel = ViewModelProviders.of(this).get(GroupViewModel.class);
        groupViewModel.getAllGroups().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable List<Group> groups) {
                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        return mainView;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

}
