package com.streamax.config.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DeviceInfoHelper extends SQLiteOpenHelper {
    public Context mContext;

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DeviceInfoHelper(Context context) {
        super(context, DatebaseConfig.DatabaseName, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("Create table DeviceInfo(id integer primary key autoincrement,dbDeviceIp varchar,dbCurrentChannel Integer,dbCurrentDay Integer,dbCurrentPlan Integer,dbCurrentTotalChannel Integer,dbCurrentTotalPlan Integer);");
    }
}
