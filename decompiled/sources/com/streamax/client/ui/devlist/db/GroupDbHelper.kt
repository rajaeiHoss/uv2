package com.streamax.client.ui.devlist.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GroupDbHelper(private val mContext: Context) :
    SQLiteOpenHelper(mContext, GroupDbBean.GroupDbName, null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_GROUP_FOR_NORMAL)
        db.execSQL(CREATE_GROUP_FOR_SERVER)
    }

    fun deleteDb() {
        mContext.deleteDatabase(GroupDbBean.GroupDbName)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(CREATE_GROUP_FOR_NORMAL)
        db.execSQL(CREATE_GROUP_FOR_SERVER)
    }

    companion object {
        private const val CREATE_GROUP_FOR_NORMAL =
            "Create table GroupForNormal(id integer primary key autoincrement,dbAccount varchar,dbPwd varchar,dbGroupName varchar,dbId Integer,dbDevName varchar,dbChannel Integer,dbFlag Integer);"
        private const val CREATE_GROUP_FOR_SERVER =
            "Create table GroupForServer(id integer primary key autoincrement,dbAccount varchar,dbPwd varchar,dbGroupName varchar,dbId Integer,dbDevName varchar,dbChannel Integer,dbFlag Integer);"
    }
}
