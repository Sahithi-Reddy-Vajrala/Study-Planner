package com.example.studyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SDBHelper extends SQLiteOpenHelper {
    public SDBHelper(Context context) {
        super(context, "StudyPlans.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table StudyPlandetails(name TEXT primary key, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists StudyPLandetails");
    }

    public Boolean insertstudyplandata(String name, String date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        if(name.isEmpty() || date.isEmpty()) {return false;}
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("date", date);
        long result=DB.insert("StudyPlandetails", null, contentValues);
        return result != -1;
    }


    public Boolean updatestudyplandata(String name, String date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        Cursor cursor = DB.rawQuery("Select * from StudyPlandetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("StudyPlandetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }}


    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from StudyPlandetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("StudyPlandetails", "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from StudyPlandetails", null);
        return cursor;

    }
}
