package com.example.studyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LDBHelper extends SQLiteOpenHelper {
    public LDBHelper(Context context) {
        super(context, "Lectures.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Lecturedetails(name TEXT primary key, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Lecturedetails");
    }

    public Boolean insertlecturedata(String name, String date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        if(name.isEmpty() || date.isEmpty()) {return false;}
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("date", date);
        long result=DB.insert("Lecturedetails", null, contentValues);
        return result != -1;
    }


    public Boolean updatelecturedata(String name, String date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        Cursor cursor = DB.rawQuery("Select * from Lecturedetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Lecturedetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }}


    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Lecturedetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Lecturedetails", "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Lecturedetails", null);
        return cursor;

    }
}
