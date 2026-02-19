package com.streamax.config.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.streamax.config.db.DatebaseConfig;
import com.streamax.utils.LogUtils;

public class DeviceInfoDao {
    private DeviceInfoHelper mHelper;

    public DeviceInfoDao(Context context) {
        this.mHelper = new DeviceInfoHelper(context);
    }

    public boolean add(DeviceInfoBean deviceInfoBean) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbDeviceIp, deviceInfoBean.mDeviceIp);
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentChannel, Integer.valueOf(deviceInfoBean.mCurrentChannel));
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentDay, Integer.valueOf(deviceInfoBean.mCurrentDay));
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentPlan, Integer.valueOf(deviceInfoBean.mCurrentPlan));
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentTotalChannel, Integer.valueOf(deviceInfoBean.mCurrentTotalChannel));
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentTotalPlan, Integer.valueOf(deviceInfoBean.mCurrentTotalPlan));
        long insert = writableDatabase.insert(DatebaseConfig.TableName, (String) null, contentValues);
        Class<?> cls = getClass();
        LogUtils.log(cls, "result->" + insert);
        writableDatabase.close();
        return insert != -1;
    }

    public int getCurrentChannel(String str) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=?", new String[]{str}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() == 0 || !query.moveToNext()) {
                query.close();
            } else {
                int i = query.getInt(query.getColumnIndex(DatebaseConfig.DeviceInfoTable.dbCurrentChannel));
                query.close();
                readableDatabase.close();
                return i;
            }
        }
        readableDatabase.close();
        return 0;
    }

    public int getCurrentDay(String str, int i) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=?", new String[]{str, "" + i}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() == 0 || !query.moveToNext()) {
                query.close();
            } else {
                int i2 = query.getInt(query.getColumnIndex(DatebaseConfig.DeviceInfoTable.dbCurrentDay));
                query.close();
                readableDatabase.close();
                return i2;
            }
        }
        readableDatabase.close();
        return 0;
    }

    public int getCurrentPlay(String str, int i, int i2) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=? anddbCurrentDay=?", new String[]{str, "" + i, "" + i2}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() == 0 || !query.moveToNext()) {
                query.close();
            } else {
                int i3 = query.getInt(query.getColumnIndex(DatebaseConfig.DeviceInfoTable.dbCurrentPlan));
                query.close();
                readableDatabase.close();
                return i3;
            }
        }
        readableDatabase.close();
        return 0;
    }

    public int getCurrentTotalChannel(String str) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=?", new String[]{str}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() == 0 || !query.moveToNext()) {
                query.close();
            } else {
                int i = query.getInt(query.getColumnIndex(DatebaseConfig.DeviceInfoTable.dbCurrentTotalChannel));
                query.close();
                readableDatabase.close();
                return i;
            }
        }
        readableDatabase.close();
        return 0;
    }

    public int getCurrentTotalPlan(String str, int i, int i2) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=?dbCurrentChannel=? anddbCurrentDay=?", new String[]{str, "" + i, "" + i2}, (String) null, (String) null, "id asc");
        if (query == null || query.getCount() == 0 || !query.moveToNext()) {
            readableDatabase.close();
            return 1;
        }
        int i3 = query.getInt(query.getColumnIndex(DatebaseConfig.DeviceInfoTable.dbCurrentTotalPlan));
        query.close();
        readableDatabase.close();
        return i3;
    }

    public boolean queryCurrentDeviceIp(String str) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=?", new String[]{str}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() != 0) {
                query.close();
                readableDatabase.close();
                return true;
            }
            query.close();
        }
        readableDatabase.close();
        return false;
    }

    public boolean queryCurrentChannel(String str, int i) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=?", new String[]{str, "" + i}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() != 0) {
                query.close();
                readableDatabase.close();
                return true;
            }
            query.close();
        }
        readableDatabase.close();
        return false;
    }

    public boolean queryCurrentDay(String str, int i, int i2) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=? anddbCurrentDay=?", new String[]{str, "" + i, "" + i2}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() != 0) {
                query.close();
                readableDatabase.close();
                return true;
            }
            query.close();
        }
        readableDatabase.close();
        return false;
    }

    public boolean queryCurrentPlay(String str, int i, int i2, int i3) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatebaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=? anddbCurrentDay=? anddbCurrentPlan=?", new String[]{str, "" + i, "" + i2, "" + i3}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() != 0) {
                query.close();
                readableDatabase.close();
                return true;
            }
            query.close();
        }
        readableDatabase.close();
        return false;
    }

    public boolean updateCurrentChannel(String str, int i) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentChannel, Integer.valueOf(i));
        int update = readableDatabase.update(DatebaseConfig.TableName, contentValues, "dbDeviceIp=?", new String[]{str});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCurrentDay(String str, int i, int i2) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentDay, Integer.valueOf(i2));
        int update = readableDatabase.update(DatebaseConfig.TableName, contentValues, "dbDeviceIp=? anddbCurrentChannel=?", new String[]{str, "" + i});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCurrentPlay(String str, int i, int i2, int i3) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentPlan, Integer.valueOf(i3));
        int update = readableDatabase.update(DatebaseConfig.TableName, contentValues, "dbDeviceIp=? anddbCurrentChannel=? anddbCurrentDay=?", new String[]{str, "" + i, "" + i2});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCurrentTotalChannel(String str, int i) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentTotalChannel, Integer.valueOf(i));
        int update = readableDatabase.update(DatebaseConfig.TableName, contentValues, "dbDeviceIp=?", new String[]{str});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCurrentTotalPlan(String str, int i, int i2, int i3) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatebaseConfig.DeviceInfoTable.dbCurrentTotalPlan, Integer.valueOf(i3));
        int update = readableDatabase.update(DatebaseConfig.TableName, contentValues, "dbDeviceIp=?dbCurrentChannel=? anddbCurrentDay=?", new String[]{str, "" + i, "" + i2});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }
}
