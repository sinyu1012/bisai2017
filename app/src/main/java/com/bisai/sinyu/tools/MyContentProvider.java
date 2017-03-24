package com.bisai.sinyu.tools;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bisai.sinyu.DB.SQLHelper;

/**
 * Created by sinyu on 2017/3/5.
 */
public class MyContentProvider extends ContentProvider {
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("com.sinyu.test", "sinyu/#", 3);//操作一条
        uriMatcher.addURI("com.sinyu.test", "sinyu", 4);
    }

    SQLHelper sqlHelper;

    @Override
    public boolean onCreate() {
        sqlHelper = new SQLHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        if (uriMatcher.match(uri) == 4) {
            SQLiteDatabase db = sqlHelper.getReadableDatabase();
            cursor = db.query("parkinginfo", new String[]{"name", "total", "yiyong", "content", "time", "state"}, selection, selectionArgs, null, null, sortOrder);

            return cursor;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        if(uriMatcher.match(uri)==4){
            return "vnd.android.cursor.dir/vnd.com.sinyu.test.sinyu";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        Uri uriReturn=null;
        if(uriMatcher.match(uri)==4){
            long row=db.insert("parkinginfo",null,values);
            uriReturn=Uri.parse("content://com.sinyu.test/sinyu/"+row);
        }

        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        int deleteRows=0;
        if(uriMatcher.match(uri)==3){//删除一跳数据
            String id=uri.getPathSegments().get(1);
            deleteRows=db.delete("parkinginfo","_id = ?",new String[]{id});

        }
        return deleteRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        int updateRows=0;
        if(uriMatcher.match(uri)==3){//更新一跳数据
            String id=uri.getPathSegments().get(1);
            updateRows=db.update("parkinginfo",values,"_id = ?",new String[]{id});

        }

        return updateRows;
    }
}
