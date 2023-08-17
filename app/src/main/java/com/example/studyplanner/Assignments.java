package com.example.studyplanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Assignments extends Fragment {
    EditText name, dob;
    Button insert, update, delete, view;
    View req;
    ADBHelper DB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        req = (View) inflater.inflate(R.layout.fragment_assignments, container, false);
        name = (EditText) req.findViewById(R.id.name);
        dob = (EditText) req.findViewById(R.id.dob);
        insert = (Button) req.findViewById(R.id.btnInsert);
        update = (Button) req.findViewById(R.id.btnUpdate);
        delete = (Button) req.findViewById(R.id.btnDelete);
        view = (Button) req.findViewById(R.id.btnView);
        DB = new ADBHelper(getActivity());
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = DB.insertassignmentdata(nameTXT, dobTXT);
                if(checkinsertdata==true)
                    Toast.makeText(getActivity(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DB.updateAssignmentdata(nameTXT, dobTXT);
                if(checkupdatedata==true)
                    Toast.makeText(getActivity(), "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(getActivity(), "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(getActivity(), "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    String k = res.getString(1);
                    buffer.append("Date :"+k.charAt(0)+k.charAt(1)+"-"+k.charAt(2)+k.charAt(3)+"-"+k.charAt(4)+k.charAt(5)+k.charAt(6)+k.charAt(7)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("Assignment Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
        return req;
    }
}