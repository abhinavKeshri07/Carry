package com.example.abhinav_11720007.fragments.carryRecyclerViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhinav_11720007.R;

import java.util.ArrayList;

public class RecyclerViewAdapterInput extends RecyclerView.Adapter<RecyclerViewAdapterInput.ViewHolder> {

    ArrayList<String> mArrayList = new ArrayList<>();

    public RecyclerViewAdapterInput(ArrayList<String> mArrayList) {
        this.mArrayList = mArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_for_recyclerview_text,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(mArrayList.get(i));
        Log.d("abhinav", "onBindViewHolder; was called");
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.recyclerTextView);
        }
    }
}
