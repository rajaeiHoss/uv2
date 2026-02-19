package com.streamax.client.ui.devlist.db;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.streamax.client.ui.Constants;
import com.streamax.client.ui.devlist.db.GroupDbBean;
import com.streamax.utils.AppProxy;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoForServer extends GroupDao {
    public String mDbAccount;
    public String mDbPwd;
    public GroupDbHelper mHelper = new GroupDbHelper(AppProxy.getContext());
    public SharedPreferences mSp;

    public GroupDaoForServer() {
        SharedPreferences sharedPreferences = AppProxy.getContext().getSharedPreferences(Constants.SpName, 1);
        this.mSp = sharedPreferences;
        this.mDbAccount = sharedPreferences.getString("dbAccount", "");
        this.mDbPwd = this.mSp.getString("dbPwd", "");
    }

    public void deleteDb() {
        this.mHelper.deleteDb();
    }

    public boolean add(String str, int i, String str2, int i2, int i3) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dbAccount", this.mDbAccount);
        contentValues.put("dbPwd", this.mDbPwd);
        contentValues.put(GroupDbBean.GroupTable.dbGroupName, str);
        contentValues.put(GroupDbBean.GroupTable.dbId, Integer.valueOf(i));
        contentValues.put(GroupDbBean.GroupTable.dbDevName, str2);
        contentValues.put(GroupDbBean.GroupTable.dbChannel, Integer.valueOf(i2));
        contentValues.put(GroupDbBean.GroupTable.dbFlag, Integer.valueOf(i3));
        long insert = writableDatabase.insert(GroupDbBean.TableName1, (String) null, contentValues);
        writableDatabase.close();
        return insert != -1;
    }

    public boolean delete(String str) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        int delete = writableDatabase.delete(GroupDbBean.TableName1, "dbAccount=? and dbPwd =? and dbGroupName =?", new String[]{this.mDbAccount, this.mDbPwd, str});
        writableDatabase.close();
        if (delete > 0) {
            return true;
        }
        return false;
    }

    public boolean delete(int i) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        int delete = writableDatabase.delete(GroupDbBean.TableName1, "dbAccount=? and dbPwd =? and dbId =?", new String[]{this.mDbAccount, this.mDbPwd, "" + i});
        writableDatabase.close();
        if (delete > 0) {
            return true;
        }
        return false;
    }

    public boolean delete(String str, int i, String str2, int i2) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        int delete = writableDatabase.delete(GroupDbBean.TableName1, "dbAccount=? and dbPwd =? and dbGroupName =? and dbId =? and dbDevName =? and dbChannel=?", new String[]{this.mDbAccount, this.mDbPwd, str, "" + i, str2, "" + i2});
        writableDatabase.close();
        if (delete > 0) {
            return true;
        }
        return false;
    }

    public boolean delete(int i, int i2) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        int delete = writableDatabase.delete(GroupDbBean.TableName1, "dbAccount=? and dbPwd =? and dbId=? and dbChannel=?", new String[]{this.mDbAccount, this.mDbPwd, "" + i, "" + i2});
        writableDatabase.close();
        if (delete > 0) {
            return true;
        }
        return false;
    }

    public boolean update(String str, int i, String str2, int i2, int i3) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroupDbBean.GroupTable.dbFlag, Integer.valueOf(i3));
        int update = writableDatabase.update(GroupDbBean.TableName1, contentValues, "dbAccount=? and dbPwd =? and dbGroupName=? and dbId=? and dbDevName=? and dbChannel=?", new String[]{this.mDbAccount, this.mDbPwd, str, "" + i, str2, "" + i2});
        writableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateDevNameById(int i, String str) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroupDbBean.GroupTable.dbDevName, str);
        int update = writableDatabase.update(GroupDbBean.TableName1, contentValues, "dbAccount=? and dbPwd=? and dbId=?", new String[]{this.mDbAccount, this.mDbPwd, "" + i});
        writableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean update(String str, String str2) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroupDbBean.GroupTable.dbGroupName, str);
        int update = writableDatabase.update(GroupDbBean.TableName1, contentValues, "dbAccount=? and dbPwd=? and dbGroupName=?", new String[]{this.mDbAccount, this.mDbPwd, str2});
        writableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean update(String str, int i) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GroupDbBean.GroupTable.dbId, Integer.valueOf(i));
        int update = writableDatabase.update(GroupDbBean.TableName1, contentValues, "dbAccount=? and dbPwd=? and dbDevName=?", new String[]{this.mDbAccount, this.mDbPwd, str});
        writableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean query(String str, int i) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(GroupDbBean.TableName1, (String[]) null, "dbAccount=? and dbPwd=? and dbGroupName=? and dbFlag=?", new String[]{this.mDbAccount, this.mDbPwd, str, "" + i}, (String) null, (String) null, "id asc");
        if (query == null || query.getCount() != 0) {
            readableDatabase.close();
            return false;
        }
        query.close();
        readableDatabase.close();
        return true;
    }

    public String queryDevNameByDevId() {
        Cursor query = this.mHelper.getReadableDatabase().query(GroupDbBean.TableName1, (String[]) null, "dbAccount=? and dbPwd=? and dbId=? ", new String[]{this.mDbAccount, this.mDbPwd, "-1"}, (String) null, (String) null, "id asc");
        return (query == null || query.getCount() == 0 || !query.moveToNext()) ? "" : query.getString(1);
    }

    public boolean query(String str) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(GroupDbBean.TableName1, (String[]) null, "dbAccount=? and dbPwd=? and dbGroupName=? ", new String[]{this.mDbAccount, this.mDbPwd, str}, (String) null, (String) null, "id asc");
        if (query == null || query.getCount() != 0) {
            readableDatabase.close();
            return false;
        }
        query.close();
        readableDatabase.close();
        return true;
    }

    public int getChCount(String str, int i) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(GroupDbBean.TableName1, (String[]) null, "dbAccount=? and dbPwd=? and dbGroupName=? and dbId=? ", new String[]{this.mDbAccount, this.mDbPwd, str, "" + i}, (String) null, (String) null, "id asc");
        if (query != null) {
            return query.getCount();
        }
        return -1;
    }

    public boolean queryByGroupAndDevName(String str, String str2, int i) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(GroupDbBean.TableName1, (String[]) null, "dbAccount=? and dbPwd=? and dbGroupName=? and dbDevName=? and dbChannel=? ", new String[]{this.mDbAccount, this.mDbPwd, str, str2, "" + i}, (String) null, (String) null, "id asc");
        if (query == null || query.getCount() != 0) {
            readableDatabase.close();
            return false;
        }
        query.close();
        readableDatabase.close();
        return true;
    }

    public boolean query(String str, int i, String str2, int i2) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(GroupDbBean.TableName1, (String[]) null, "dbAccount=? and dbPwd=? and dbGroupName=? and dbId=? and dbDevName=? and dbChannel=? ", new String[]{this.mDbAccount, this.mDbPwd, str, "" + i, str2, "" + i2}, (String) null, (String) null, "id asc");
        if (query == null || query.getCount() != 0) {
            readableDatabase.close();
            return false;
        }
        query.close();
        readableDatabase.close();
        return true;
    }

    public boolean queryDevName(String str) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(GroupDbBean.TableName1, (String[]) null, "dbAccount=? and dbPwd=? and dbDevName=?", new String[]{this.mDbAccount, this.mDbPwd, str}, (String) null, (String) null, "id asc");
        if (query == null || query.getCount() != 0) {
            readableDatabase.close();
            return false;
        }
        query.close();
        readableDatabase.close();
        return true;
    }

    public List<GroupBean> getGroupDatasByName(String str) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("select * from GroupForServer where dbGroupName = ? ", new String[]{str});
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                GroupBean groupBean = new GroupBean();
                groupBean.dbAccont = rawQuery.getString(1);
                groupBean.dbPwd = rawQuery.getString(2);
                groupBean.dbGroupName = rawQuery.getString(3);
                groupBean.dbId = rawQuery.getInt(4);
                groupBean.dbDevName = rawQuery.getString(5);
                groupBean.dbChannel = rawQuery.getInt(6);
                groupBean.dbFlag = rawQuery.getInt(7);
                if (this.mDbAccount.equals(groupBean.dbAccont) && this.mDbPwd.equals(groupBean.dbPwd)) {
                    arrayList.add(groupBean);
                }
            }
            rawQuery.close();
        }
        readableDatabase.close();
        return arrayList;
    }

    public List<GroupBean> getGroupDatas() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("select * from GroupForServer", (String[]) null);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                GroupBean groupBean = new GroupBean();
                groupBean.dbAccont = rawQuery.getString(1);
                groupBean.dbPwd = rawQuery.getString(2);
                groupBean.dbGroupName = rawQuery.getString(3);
                groupBean.dbId = rawQuery.getInt(4);
                groupBean.dbDevName = rawQuery.getString(5);
                groupBean.dbChannel = rawQuery.getInt(6);
                groupBean.dbFlag = rawQuery.getInt(7);
                if (this.mDbAccount.equals(groupBean.dbAccont) && this.mDbPwd.equals(groupBean.dbPwd)) {
                    arrayList.add(groupBean);
                }
            }
            rawQuery.close();
        }
        readableDatabase.close();
        return arrayList;
    }

    public boolean isEmpty() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("select * from GroupForServer", (String[]) null);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                GroupBean groupBean = new GroupBean();
                groupBean.dbAccont = rawQuery.getString(1);
                groupBean.dbPwd = rawQuery.getString(2);
                groupBean.dbGroupName = rawQuery.getString(3);
                groupBean.dbId = rawQuery.getInt(4);
                groupBean.dbDevName = rawQuery.getString(5);
                groupBean.dbChannel = rawQuery.getInt(6);
                groupBean.dbFlag = rawQuery.getInt(7);
                if (this.mDbAccount.equals(groupBean.dbAccont) && this.mDbPwd.equals(groupBean.dbPwd)) {
                    arrayList.add(groupBean);
                }
            }
            rawQuery.close();
        }
        readableDatabase.close();
        if (arrayList.size() <= 0) {
            return true;
        }
        return false;
    }
}
