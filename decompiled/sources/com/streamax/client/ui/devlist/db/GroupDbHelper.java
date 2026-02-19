package com.streamax.client.ui.devlist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GroupDbHelper extends SQLiteOpenHelper {
    private Context mContext;

    public GroupDbHelper(Context context) {
        super(context, GroupDbBean.GroupDbName, (SQLiteDatabase.CursorFactory) null, 2);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("Create table GroupForNormal(id integer primary key autoincrement,dbAccount varchar,dbPwd varchar,dbGroupName varchar,dbId Integer,dbDevName varchar,dbChannel Integer,dbFlag Integer);");
        sQLiteDatabase.execSQL("Create table GroupForServer(id integer primary key autoincrement,dbAccount varchar,dbPwd varchar,dbGroupName varchar,dbId Integer,dbDevName varchar,dbChannel Integer,dbFlag Integer);");
    }

    public void deleteDb() {
        this.mContext.deleteDatabase(GroupDbBean.GroupDbName);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("Create table GroupForNormal(id integer primary key autoincrement,dbAccount varchar,dbPwd varchar,dbGroupName varchar,dbId Integer,dbDevName varchar,dbChannel Integer,dbFlag Integer);");
        sQLiteDatabase.execSQL("Create table GroupForServer(id integer primary key autoincrement,dbAccount varchar,dbPwd varchar,dbGroupName varchar,dbId Integer,dbDevName varchar,dbChannel Integer,dbFlag Integer);");
    }
}
