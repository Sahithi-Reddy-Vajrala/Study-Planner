package com.example.studyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EDBHelper extends SQLiteOpenHelper {
    public EDBHelper(Context context) {
        super(context, "Exams.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Examdetails(name TEXT primary key, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Examdetails");
    }

    public Boolean insertexamdata(String name, String date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(name.isEmpty() || date.isEmpty()) {return false;}
        contentValues.put("name", name);
        contentValues.put("date", date);
        long result=DB.insert("Examdetails",null, contentValues);
        return result != -1;
    }


    public Boolean updateexamdata(String name, String date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        Cursor cursor = DB.rawQuery("Select * from Examdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Examdetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }}


    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Examdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Examdetails", "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Examdetails", null);
        return cursor;

    }
}
