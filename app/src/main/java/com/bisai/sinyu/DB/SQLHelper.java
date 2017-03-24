package com.bisai.sinyu.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sinyu on 2017/3/1.
 */
public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(Context context) {
        super(context, "APP.db", null, 2);
        Log.d("DB","创建DB--1");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB","创建DB--2");
        db.execSQL("create table parkinginfo (_id integer primary key autoincrement,name varchar(20),total int,yiyong int,content varchar(100),time varchar(20),state int,collection int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
