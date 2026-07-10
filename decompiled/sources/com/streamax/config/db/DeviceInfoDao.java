package com.streamax.config.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.streamax.config.db.DatabaseConfig;
import com.streamax.utils.LogUtils;

public class DeviceInfoDao {
    private DeviceInfoHelper mHelper;

    public DeviceInfoDao(Context context) {
        this.mHelper = new DeviceInfoHelper(context);
    }

    public boolean add(DeviceInfoBean deviceInfoBean) {
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbDeviceIp, deviceInfoBean.mDeviceIp);
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentChannel, Integer.valueOf(deviceInfoBean.mCurrentChannel));
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentDay, Integer.valueOf(deviceInfoBean.mCurrentDay));
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentPlan, Integer.valueOf(deviceInfoBean.mCurrentPlan));
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentTotalChannel, Integer.valueOf(deviceInfoBean.mCurrentTotalChannel));
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentTotalPlan, Integer.valueOf(deviceInfoBean.mCurrentTotalPlan));
        long insert = writableDatabase.insert(DatabaseConfig.TableName, (String) null, contentValues);
        Class<?> cls = getClass();
        LogUtils.log(cls, "result->" + insert);
        writableDatabase.close();
        return insert != -1;
    }

    public int getCurrentChannel(String deviceIp) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=?", new String[]{deviceIp}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() == 0 || !query.moveToNext()) {
                query.close();
            } else {
                int currentChannel = query.getInt(query.getColumnIndex(DatabaseConfig.DeviceInfoTable.dbCurrentChannel));
                query.close();
                readableDatabase.close();
                return currentChannel;
            }
        }
        readableDatabase.close();
        return 0;
    }

    public int getCurrentDay(String deviceIp, int channel) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=?", new String[]{deviceIp, "" + channel}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() == 0 || !query.moveToNext()) {
                query.close();
            } else {
                int currentDay = query.getInt(query.getColumnIndex(DatabaseConfig.DeviceInfoTable.dbCurrentDay));
                query.close();
                readableDatabase.close();
                return currentDay;
            }
        }
        readableDatabase.close();
        return 0;
    }

    public int getCurrentPlay(String deviceIp, int channel, int day) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=? anddbCurrentDay=?", new String[]{deviceIp, "" + channel, "" + day}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() == 0 || !query.moveToNext()) {
                query.close();
            } else {
                int currentPlan = query.getInt(query.getColumnIndex(DatabaseConfig.DeviceInfoTable.dbCurrentPlan));
                query.close();
                readableDatabase.close();
                return currentPlan;
            }
        }
        readableDatabase.close();
        return 0;
    }

    public int getCurrentTotalChannel(String deviceIp) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=?", new String[]{deviceIp}, (String) null, (String) null, "id asc");
        if (query != null) {
            if (query.getCount() == 0 || !query.moveToNext()) {
                query.close();
            } else {
                int totalChannel = query.getInt(query.getColumnIndex(DatabaseConfig.DeviceInfoTable.dbCurrentTotalChannel));
                query.close();
                readableDatabase.close();
                return totalChannel;
            }
        }
        readableDatabase.close();
        return 0;
    }

    public int getCurrentTotalPlan(String deviceIp, int channel, int day) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=?dbCurrentChannel=? anddbCurrentDay=?", new String[]{deviceIp, "" + channel, "" + day}, (String) null, (String) null, "id asc");
        if (query == null || query.getCount() == 0 || !query.moveToNext()) {
            readableDatabase.close();
            return 1;
        }
        int totalPlan = query.getInt(query.getColumnIndex(DatabaseConfig.DeviceInfoTable.dbCurrentTotalPlan));
        query.close();
        readableDatabase.close();
        return totalPlan;
    }

    public boolean queryCurrentDeviceIp(String deviceIp) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=?", new String[]{deviceIp}, (String) null, (String) null, "id asc");
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

    public boolean queryCurrentChannel(String deviceIp, int channel) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=?", new String[]{deviceIp, "" + channel}, (String) null, (String) null, "id asc");
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

    public boolean queryCurrentDay(String deviceIp, int channel, int day) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=? anddbCurrentDay=?", new String[]{deviceIp, "" + channel, "" + day}, (String) null, (String) null, "id asc");
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

    public boolean queryCurrentPlay(String deviceIp, int channel, int day, int plan) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(DatabaseConfig.TableName, (String[]) null, "dbDeviceIp=? anddbCurrentChannel=? anddbCurrentDay=? anddbCurrentPlan=?", new String[]{deviceIp, "" + channel, "" + day, "" + plan}, (String) null, (String) null, "id asc");
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

    public boolean updateCurrentChannel(String deviceIp, int channel) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentChannel, Integer.valueOf(channel));
        int update = readableDatabase.update(DatabaseConfig.TableName, contentValues, "dbDeviceIp=?", new String[]{deviceIp});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCurrentDay(String deviceIp, int channel, int day) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentDay, Integer.valueOf(day));
        int update = readableDatabase.update(DatabaseConfig.TableName, contentValues, "dbDeviceIp=? anddbCurrentChannel=?", new String[]{deviceIp, "" + channel});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCurrentPlay(String deviceIp, int channel, int day, int plan) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentPlan, Integer.valueOf(plan));
        int update = readableDatabase.update(DatabaseConfig.TableName, contentValues, "dbDeviceIp=? anddbCurrentChannel=? anddbCurrentDay=?", new String[]{deviceIp, "" + channel, "" + day});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCurrentTotalChannel(String deviceIp, int totalChannel) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentTotalChannel, Integer.valueOf(totalChannel));
        int update = readableDatabase.update(DatabaseConfig.TableName, contentValues, "dbDeviceIp=?", new String[]{deviceIp});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }

    public boolean updateCurrentTotalPlan(String deviceIp, int channel, int day, int totalPlan) {
        SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConfig.DeviceInfoTable.dbCurrentTotalPlan, Integer.valueOf(totalPlan));
        int update = readableDatabase.update(DatabaseConfig.TableName, contentValues, "dbDeviceIp=?dbCurrentChannel=? anddbCurrentDay=?", new String[]{deviceIp, "" + channel, "" + day});
        readableDatabase.close();
        if (update > 0) {
            return true;
        }
        return false;
    }
}
