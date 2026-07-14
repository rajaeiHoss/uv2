package com.streamax.config.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DeviceInfoHelper extends SQLiteOpenHelper {
    public Context mContext;

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }

    public DeviceInfoHelper(Context context) {
        super(context, DatabaseConfig.DatabaseName, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL("Create table DeviceInfo(id integer primary key autoincrement,dbDeviceIp varchar,dbCurrentChannel Integer,dbCurrentDay Integer,dbCurrentPlan Integer,dbCurrentTotalChannel Integer,dbCurrentTotalPlan Integer);");
    }
}
