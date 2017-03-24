package com.bisai.sinyu.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinyu on 2017/3/1.
 */
public class ParkingDao {
    private SQLHelper helper;

    public ParkingDao(Context context) {
        helper = new SQLHelper(context);
        helper.getWritableDatabase();

    }

    public long addParking(String name, int total, int yiyong, String content, String time, int state, int collect) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("total", total);
        values.put("yiyong", yiyong);
        values.put("content", content);
        values.put("time", time);
        values.put("state", state);
        values.put("collection", collect);
        long i = db.insert("parkinginfo", null, values);
        db.close();
        return i;
    }

    public List<ParkingInfo> findParking() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<ParkingInfo> infos = new ArrayList<ParkingInfo>();
        Cursor cursor = db.query("parkinginfo", new String[]{"name", "total", "yiyong", "content", "time", "state", "collection"}, null, null, null, null, "state desc");
        while (cursor.moveToNext()) {
            ParkingInfo info = new ParkingInfo();
            info.setName(cursor.getString(0));
            info.setTotal(cursor.getInt(1));
            info.setYiyong(cursor.getInt(2));
            info.setContent(cursor.getString(3));
            info.setTime(cursor.getString(4));
            info.setState(cursor.getInt(5));
            info.setCollect(cursor.getInt(6));
            infos.add(info);
        }
        cursor.close();
        db.close();
        return infos;
    }

}
