package com.streamax.client.ui.devlist.db

import android.content.ContentValues
import android.content.SharedPreferences
import android.database.Cursor
import com.streamax.client.ui.Constants
import com.streamax.utils.AppProxy

class GroupDaoForNormal : GroupDao() {
    private var mDbAccount: String
    private var mDbPwd: String
    private val mHelper = GroupDbHelper(AppProxy.getContext())
    private var mSp: SharedPreferences

    init {
        val sharedPreferences = AppProxy.getContext().getSharedPreferences(Constants.SpName, 1)
        mSp = sharedPreferences
        mDbAccount = sharedPreferences.getString("dbAccount", "") ?: ""
        mDbPwd = mSp.getString("dbPwd", "") ?: ""
    }

    override fun deleteDb() {
        mHelper.deleteDb()
    }

    override fun add(groupName: String, deviceId: Int, deviceName: String, channel: Int, flag: Int): Boolean {
        val database = mHelper.writableDatabase
        val values = ContentValues()
        values.put("dbAccount", mDbAccount)
        values.put("dbPwd", mDbPwd)
        values.put(GroupDbBean.GroupTable.dbGroupName, groupName)
        values.put(GroupDbBean.GroupTable.dbId, deviceId)
        values.put(GroupDbBean.GroupTable.dbDevName, deviceName)
        values.put(GroupDbBean.GroupTable.dbChannel, channel)
        values.put(GroupDbBean.GroupTable.dbFlag, flag)
        val insertedRowId = database.insert(GroupDbBean.TableName0, null, values)
        database.close()
        return insertedRowId != -1L
    }

    override fun delete(groupName: String): Boolean {
        val database = mHelper.writableDatabase
        val deletedRows = database.delete(GroupDbBean.TableName0, "dbGroupName=?", arrayOf(groupName))
        database.close()
        return deletedRows > 0
    }

    override fun delete(deviceId: Int): Boolean {
        val database = mHelper.writableDatabase
        val deletedRows = database.delete(GroupDbBean.TableName0, "dbId=?", arrayOf("$deviceId"))
        database.close()
        return deletedRows > 0
    }

    override fun delete(groupName: String, deviceId: Int, deviceName: String, channel: Int): Boolean {
        val database = mHelper.writableDatabase
        val deletedRows = database.delete(
            GroupDbBean.TableName0,
            "dbGroupName=? and dbId=? and dbDevName=? and dbChannel=?",
            arrayOf(groupName, "$deviceId", deviceName, "$channel")
        )
        database.close()
        return deletedRows > 0
    }

    override fun delete(deviceId: Int, channel: Int): Boolean {
        val database = mHelper.writableDatabase
        val deletedRows = database.delete(
            GroupDbBean.TableName0,
            "dbId=? and dbChannel=?",
            arrayOf("$deviceId", "$channel")
        )
        database.close()
        return deletedRows > 0
    }

    override fun update(groupName: String, deviceId: Int, deviceName: String, channel: Int, flag: Int): Boolean {
        val database = mHelper.writableDatabase
        val values = ContentValues()
        values.put(GroupDbBean.GroupTable.dbFlag, flag)
        val updatedRows = database.update(
            GroupDbBean.TableName0,
            values,
            "dbGroupName=? and dbId=? and dbDevName=? and dbChannel=?",
            arrayOf(groupName, "$deviceId", deviceName, "$channel")
        )
        database.close()
        return updatedRows > 0
    }

    override fun update(newGroupName: String, oldGroupName: String): Boolean {
        val database = mHelper.writableDatabase
        val values = ContentValues()
        values.put(GroupDbBean.GroupTable.dbGroupName, newGroupName)
        val updatedRows = database.update(
            GroupDbBean.TableName0,
            values,
            "dbGroupName=?",
            arrayOf(oldGroupName)
        )
        database.close()
        return updatedRows > 0
    }

    override fun update(deviceName: String, deviceId: Int): Boolean {
        val database = mHelper.writableDatabase
        val values = ContentValues()
        values.put(GroupDbBean.GroupTable.dbId, deviceId)
        val updatedRows = database.update(
            GroupDbBean.TableName0,
            values,
            "dbDevName=?",
            arrayOf(deviceName)
        )
        database.close()
        return updatedRows > 0
    }

    override fun updateDevNameById(deviceId: Int, deviceName: String): Boolean {
        val database = mHelper.writableDatabase
        val values = ContentValues()
        values.put(GroupDbBean.GroupTable.dbDevName, deviceName)
        val updatedRows = database.update(
            GroupDbBean.TableName0,
            values,
            "dbId=?",
            arrayOf("$deviceId")
        )
        database.close()
        return updatedRows > 0
    }

    override fun query(groupName: String): Boolean =
        queryIsEmpty("dbGroupName=?", arrayOf(groupName))

    override fun query(groupName: String, flag: Int): Boolean =
        queryIsEmpty("dbGroupName=? and dbFlag=?", arrayOf(groupName, "$flag"))

    override fun queryDevName(deviceName: String): Boolean =
        queryIsEmpty("dbDevName=?", arrayOf(deviceName))

    override fun queryDevNameByDevId(): String {
        val cursor = mHelper.readableDatabase.query(
            GroupDbBean.TableName0,
            null,
            "dbId=?",
            arrayOf("-1"),
            null,
            null,
            "id asc"
        )
        return if (cursor == null || cursor.count == 0 || !cursor.moveToNext()) "" else cursor.getString(1)
    }

    override fun queryByGroupAndDevName(groupName: String, deviceName: String, channel: Int): Boolean =
        queryIsEmpty("dbGroupName=? and dbDevName=? and dbChannel=?", arrayOf(groupName, deviceName, "$channel"))

    override fun query(groupName: String, deviceId: Int, deviceName: String, channel: Int): Boolean =
        queryIsEmpty(
            "dbGroupName=? and dbId=? and dbDevName=? and dbChannel=?",
            arrayOf(groupName, "$deviceId", deviceName, "$channel")
        )

    override fun getChCount(groupName: String, deviceId: Int): Int {
        val database = mHelper.readableDatabase
        val cursor = database.query(
            GroupDbBean.TableName0,
            null,
            "dbGroupName=? and dbId=?",
            arrayOf(groupName, "$deviceId"),
            null,
            null,
            "id asc"
        )
        if (cursor != null) {
            return cursor.count
        }
        return -1
    }

    override fun getGroupDatasByName(groupName: String): List<GroupBean> {
        val groups = ArrayList<GroupBean>()
        val database = mHelper.readableDatabase
        val cursor = database.rawQuery("select * from GroupForNormal where dbGroupName =?", arrayOf(groupName))
        if (cursor != null) {
            while (cursor.moveToNext()) {
                groups.add(cursor.toGroupBean())
            }
            cursor.close()
        }
        database.close()
        return groups
    }

    override fun getGroupDatas(): List<GroupBean> {
        val groups = ArrayList<GroupBean>()
        val database = mHelper.readableDatabase
        val cursor = database.rawQuery("select * from GroupForNormal", null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                groups.add(cursor.toGroupBean())
            }
            cursor.close()
        }
        database.close()
        return groups
    }

    override fun isEmpty(): Boolean {
        val groups = ArrayList<GroupBean>()
        val database = mHelper.readableDatabase
        val cursor = database.rawQuery("select * from GroupForNormal", null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                groups.add(cursor.toGroupBean())
            }
            cursor.close()
        }
        database.close()
        return groups.size <= 0
    }

    private fun queryIsEmpty(selection: String, selectionArgs: Array<String>): Boolean {
        val database = mHelper.readableDatabase
        val cursor = database.query(GroupDbBean.TableName0, null, selection, selectionArgs, null, null, "id asc")
        if (cursor == null || cursor.count != 0) {
            database.close()
            return false
        }
        cursor.close()
        database.close()
        return true
    }

    private fun Cursor.toGroupBean(): GroupBean {
        val groupBean = GroupBean()
        groupBean.dbAccont = getString(1)
        groupBean.dbPwd = getString(2)
        groupBean.dbGroupName = getString(3)
        groupBean.dbId = getInt(4)
        groupBean.dbDevName = getString(5)
        groupBean.dbChannel = getInt(6)
        groupBean.dbFlag = getInt(7)
        return groupBean
    }
}
