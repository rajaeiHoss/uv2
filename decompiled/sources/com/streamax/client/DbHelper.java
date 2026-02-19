package com.streamax.client;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME = "streaming.db";
    public static String DEVICE_TABLE_NAME = "DEVICES";
    public static final int SQL_VERSION = 3;
    public static final String TAG = "DbHelper";
    public static boolean alartTable = false;

    public DbHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, DATABASENAME, cursorFactory, 3);
        Log.v(TAG, "[DbHelper]" + context.toString());
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.v(TAG, "DbHelper oncreate");
        sQLiteDatabase.execSQL("Create table IF NOT EXISTS " + DEVICE_TABLE_NAME + "(id integer primary key autoincrement,deviceName varchar,deviceIp varchar,mediaPort integer,webPort integer,channelNum integer,username varchar,password varchar,push integer,linkmode varchar default '');");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DEVICE_TABLE_NAME);
        onCreate(sQLiteDatabase);
    }

    public boolean insert(DevInfoBean devInfoBean) {
        Log.v(TAG, "insert__beigin");
        if (devInfoBean == null) {
            return false;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("deviceName", devInfoBean.mDevName);
        contentValues.put("deviceIp", devInfoBean.mDevIp);
        contentValues.put("mediaPort", Integer.valueOf(devInfoBean.mMediaPort));
        contentValues.put("webPort", Integer.valueOf(devInfoBean.mWebPort));
        contentValues.put("channelNum", Integer.valueOf(devInfoBean.mChCounts));
        contentValues.put("username", devInfoBean.mUsername);
        contentValues.put("password", devInfoBean.mPwd);
        contentValues.put("push", Integer.valueOf(devInfoBean.mPush));
        contentValues.put("linkmode", devInfoBean.mLinkMode);
        long insert = writableDatabase.insert(DEVICE_TABLE_NAME, (String) null, contentValues);
        Log.v(TAG, "insert__nId = " + insert);
        writableDatabase.close();
        return true;
    }

    public boolean delete(DevInfoBean devInfoBean) {
        if (devInfoBean == null) {
            return false;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(DEVICE_TABLE_NAME, "deviceName=?", new String[]{devInfoBean.mDevName});
        writableDatabase.close();
        return true;
    }

    public boolean delete(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(DEVICE_TABLE_NAME, "id=?", new String[]{Integer.toString(i)});
        writableDatabase.close();
        return true;
    }

    public boolean queryDeviceName(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        SQLiteDatabase sQLiteDatabase = readableDatabase;
        Cursor query = sQLiteDatabase.query(DEVICE_TABLE_NAME, (String[]) null, "devicename=?", new String[]{str}, (String) null, (String) null, "id asc");
        if (query.getCount() == 0) {
            query.close();
            return false;
        }
        query.close();
        readableDatabase.close();
        return true;
    }

    public int queryDevChCount() {
        return getReadableDatabase().query(DEVICE_TABLE_NAME, (String[]) null, "channelNum=?", new String[]{"0"}, (String) null, (String) null, "id asc").getCount();
    }

    public DevInfoBean query(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        SQLiteDatabase sQLiteDatabase = readableDatabase;
        Cursor query = sQLiteDatabase.query(DEVICE_TABLE_NAME, (String[]) null, "devicename=?", new String[]{str}, (String) null, (String) null, "id asc");
        if (query.getCount() == 0) {
            query.close();
            return null;
        } else if (!query.moveToNext()) {
            return null;
        } else {
            DevInfoBean devInfoBean = new DevInfoBean();
            devInfoBean.mDevId = query.getInt(query.getColumnIndex("id"));
            devInfoBean.mDevName = query.getString(query.getColumnIndex("deviceName"));
            devInfoBean.mDevIp = query.getString(query.getColumnIndex("deviceIp"));
            devInfoBean.mMediaPort = query.getInt(query.getColumnIndex("mediaPort"));
            devInfoBean.mWebPort = query.getInt(query.getColumnIndex("webPort"));
            devInfoBean.mChCounts = query.getInt(query.getColumnIndex("channelNum"));
            devInfoBean.mUsername = query.getString(query.getColumnIndex("username"));
            devInfoBean.mPwd = query.getString(query.getColumnIndex("password"));
            devInfoBean.mPush = query.getInt(query.getColumnIndex("push"));
            devInfoBean.mLinkMode = query.getString(query.getColumnIndex("linkmode"));
            query.close();
            readableDatabase.close();
            return devInfoBean;
        }
    }

    public int getIdByName(String str) {
        Cursor query = getReadableDatabase().query(DEVICE_TABLE_NAME, (String[]) null, "deviceName=?", new String[]{str}, (String) null, (String) null, "id asc");
        if (query == null || !query.moveToNext()) {
            return -1;
        }
        return query.getInt(0);
    }

    public DevInfoBean query(int i) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        SQLiteDatabase sQLiteDatabase = readableDatabase;
        Cursor query = sQLiteDatabase.query(DEVICE_TABLE_NAME, (String[]) null, "id=?", new String[]{Integer.toString(i)}, (String) null, (String) null, "id asc");
        if (query.moveToNext()) {
            DevInfoBean devInfoBean = new DevInfoBean();
            devInfoBean.mDevId = query.getInt(query.getColumnIndex("id"));
            devInfoBean.mDevName = query.getString(query.getColumnIndex("deviceName"));
            devInfoBean.mDevIp = query.getString(query.getColumnIndex("deviceIp"));
            devInfoBean.mMediaPort = query.getInt(query.getColumnIndex("mediaPort"));
            devInfoBean.mWebPort = query.getInt(query.getColumnIndex("webPort"));
            devInfoBean.mChCounts = query.getInt(query.getColumnIndex("channelNum"));
            devInfoBean.mUsername = query.getString(query.getColumnIndex("username"));
            devInfoBean.mPwd = query.getString(query.getColumnIndex("password"));
            devInfoBean.mPush = query.getInt(query.getColumnIndex("push"));
            devInfoBean.mLinkMode = query.getString(query.getColumnIndex("linkmode"));
            query.close();
            readableDatabase.close();
            return devInfoBean;
        }
        query.close();
        readableDatabase.close();
        return null;
    }

    public List<DevInfoBean> getlist() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor query = readableDatabase.query(DEVICE_TABLE_NAME, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "id asc");
        query.moveToFirst();
        while (!query.isAfterLast()) {
            DevInfoBean devInfoBean = new DevInfoBean();
            devInfoBean.mDevId = query.getInt(query.getColumnIndex("id"));
            devInfoBean.mDevName = query.getString(query.getColumnIndex("deviceName"));
            devInfoBean.mDevIp = query.getString(query.getColumnIndex("deviceIp"));
            devInfoBean.mMediaPort = query.getInt(query.getColumnIndex("mediaPort"));
            devInfoBean.mWebPort = query.getInt(query.getColumnIndex("webPort"));
            devInfoBean.mChCounts = query.getInt(query.getColumnIndex("channelNum"));
            devInfoBean.mUsername = query.getString(query.getColumnIndex("username"));
            devInfoBean.mPwd = query.getString(query.getColumnIndex("password"));
            devInfoBean.mPush = query.getInt(query.getColumnIndex("push"));
            devInfoBean.mLinkMode = query.getString(query.getColumnIndex("linkmode"));
            arrayList.add(devInfoBean);
            query.moveToNext();
        }
        query.close();
        readableDatabase.close();
        return arrayList;
    }

    public boolean Update(int i, DevInfoBean devInfoBean) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("deviceName", devInfoBean.mDevName);
        contentValues.put("deviceIp", devInfoBean.mDevIp);
        contentValues.put("mediaPort", Integer.valueOf(devInfoBean.mMediaPort));
        contentValues.put("webPort", Integer.valueOf(devInfoBean.mWebPort));
        contentValues.put("channelNum", Integer.valueOf(devInfoBean.mChCounts));
        contentValues.put("username", devInfoBean.mUsername);
        contentValues.put("password", devInfoBean.mPwd);
        contentValues.put("push", Integer.valueOf(devInfoBean.mPush));
        contentValues.put("linkmode", devInfoBean.mLinkMode);
        int update = writableDatabase.update(DEVICE_TABLE_NAME, contentValues, "id=?", new String[]{Integer.toString(i)});
        writableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public int Update(DevInfoBean devInfoBean) {
        if (devInfoBean == null) {
            return -1;
        }
        if (queryDeviceName(devInfoBean.mDevName)) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("deviceName", devInfoBean.mDevName);
            contentValues.put("deviceIp", devInfoBean.mDevIp);
            contentValues.put("mediaPort", Integer.valueOf(devInfoBean.mMediaPort));
            contentValues.put("webPort", Integer.valueOf(devInfoBean.mWebPort));
            contentValues.put("username", devInfoBean.mUsername);
            contentValues.put("password", devInfoBean.mPwd);
            contentValues.put("channelNum", Integer.valueOf(devInfoBean.mChCounts));
            contentValues.put("push", Integer.valueOf(devInfoBean.mPush));
            contentValues.put("linkmode", devInfoBean.mLinkMode);
            int update = writableDatabase.update(DEVICE_TABLE_NAME, contentValues, "deviceName=?", new String[]{devInfoBean.mDevName});
            writableDatabase.close();
            if (update > 0) {
                return 1;
            }
            return -1;
        } else if (insert(devInfoBean)) {
            return 2;
        } else {
            return -1;
        }
    }
}
