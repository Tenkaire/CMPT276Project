package com.group8.dragcode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context) {
        super(context, "dragcode.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StatsModel.SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(StatsModel.SQL_DELETE);
        this.onCreate(db);
    }
}
