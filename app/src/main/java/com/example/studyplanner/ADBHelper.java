package com.example.studyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ADBHelper extends SQLiteOpenHelper {
    public ADBHelper(Context context) {
        super(context, "Assignments.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Assignmentdetails(name TEXT primary key, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Assignmentdetails");
    }

    public Boolean insertassignmentdata(String name, String dob)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(name.isEmpty() || dob.isEmpty()) {return false;}
        contentValues.put("name", name);
        contentValues.put("dob", dob);
        long result=DB.insert("Assignmentdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateAssignmentdata(String name, String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("Select * from Assignmentdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Assignmentdetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }}


    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Assignmentdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Assignmentdetails", "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Assignmentdetails", null);
        return cursor;

    }
}
