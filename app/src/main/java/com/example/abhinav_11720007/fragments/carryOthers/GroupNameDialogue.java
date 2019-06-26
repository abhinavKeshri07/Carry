package com.example.abhinav_11720007.fragments.carryOthers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.abhinav_11720007.R;

public class GroupNameDialogue extends AppCompatDialogFragment {
    private EditText editTextGroupName;
    GroupNameDialogueListener listener;
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try{
            listener = (GroupNameDialogueListener)context;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(getActivity().toString()+ " must implement GroupNameDialogueListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.group_name_dialogue,null);
        editTextGroupName = view.findViewById(R.id.groupName );
        builder.setView(view).setTitle("Group Name").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String GroupName = editTextGroupName.getText().toString();
                listener.applyText(GroupName);
            }
        });
        return builder.create();
    }
    public interface GroupNameDialogueListener{
        void applyText(String GroupName);
    }
}
