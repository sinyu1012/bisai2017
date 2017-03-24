package com.bisai.sinyu.tools;

import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by sinyu on 2017/3/5.
 */
public class MyObserver extends ContentObserver {
  //  UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    Handler mHandler;
    Context mContext;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public MyObserver(Context context, Handler handler) {
        super(handler);
        mHandler = handler;
        mContext = context;
    }

    @Override
    public void onChange(boolean selfChange) {

        Uri uri = Uri.parse("content://sms/");
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, "date desc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String num = cursor.getString(cursor.getColumnIndex("address"));
                String body = cursor.getString(cursor.getColumnIndex("body"));
                if (body.contains("您正在使用APP")) {

                    Message msg = new Message();
                    try {
                        msg.obj = body.split(":")[1];
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    break;
                }
            }
        }
    }
}
