package com.streamax.client.ui.devlist.db

import android.content.ContentValues
import android.content.SharedPreferences
import android.database.Cursor
import com.streamax.client.ui.Constants
import com.streamax.utils.AppProxy

class GroupDaoForServer : GroupDao() {
    @JvmField var mDbAccount: String
    @JvmField var mDbPwd: String
    @JvmField val mHelper = GroupDbHelper(AppProxy.getContext())
    @JvmField var mSp: SharedPreferences

    init {
        val sharedPreferences = AppProxy.getContext().getSharedPreferences(Constants.SpName, 1)
        mSp = sharedPreferences
        mDbAccount = sharedPreferences.getString("dbAccount", "") ?: ""
        mDbPwd = mSp.getString("dbPwd", "") ?: ""
    }

    override fun deleteDb() {
        mHelper.deleteDb()
    }

    override fun add(str: String, i: Int, str2: String, i2: Int, i3: Int): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("dbAccount", mDbAccount)
        contentValues.put("dbPwd", mDbPwd)
        contentValues.put(GroupDbBean.GroupTable.dbGroupName, str)
        contentValues.put(GroupDbBean.GroupTable.dbId, i)
        contentValues.put(GroupDbBean.GroupTable.dbDevName, str2)
        contentValues.put(GroupDbBean.GroupTable.dbChannel, i2)
        contentValues.put(GroupDbBean.GroupTable.dbFlag, i3)
        val insert = writableDatabase.insert(GroupDbBean.TableName1, null, contentValues)
        writableDatabase.close()
        return insert != -1L
    }

    override fun delete(str: String): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val delete = writableDatabase.delete(
            GroupDbBean.TableName1,
            "dbAccount=? and dbPwd =? and dbGroupName =?",
            arrayOf(mDbAccount, mDbPwd, str)
        )
        writableDatabase.close()
        return delete > 0
    }

    override fun delete(i: Int): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val delete = writableDatabase.delete(
            GroupDbBean.TableName1,
            "dbAccount=? and dbPwd =? and dbId =?",
            arrayOf(mDbAccount, mDbPwd, "$i")
        )
        writableDatabase.close()
        return delete > 0
    }

    override fun delete(str: String, i: Int, str2: String, i2: Int): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val delete = writableDatabase.delete(
            GroupDbBean.TableName1,
            "dbAccount=? and dbPwd =? and dbGroupName =? and dbId =? and dbDevName =? and dbChannel=?",
            arrayOf(mDbAccount, mDbPwd, str, "$i", str2, "$i2")
        )
        writableDatabase.close()
        return delete > 0
    }

    override fun delete(i: Int, i2: Int): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val delete = writableDatabase.delete(
            GroupDbBean.TableName1,
            "dbAccount=? and dbPwd =? and dbId=? and dbChannel=?",
            arrayOf(mDbAccount, mDbPwd, "$i", "$i2")
        )
        writableDatabase.close()
        return delete > 0
    }

    override fun update(str: String, i: Int, str2: String, i2: Int, i3: Int): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(GroupDbBean.GroupTable.dbFlag, i3)
        val update = writableDatabase.update(
            GroupDbBean.TableName1,
            contentValues,
            "dbAccount=? and dbPwd =? and dbGroupName=? and dbId=? and dbDevName=? and dbChannel=?",
            arrayOf(mDbAccount, mDbPwd, str, "$i", str2, "$i2")
        )
        writableDatabase.close()
        return update > 0
    }

    override fun updateDevNameById(i: Int, str: String): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(GroupDbBean.GroupTable.dbDevName, str)
        val update = writableDatabase.update(
            GroupDbBean.TableName1,
            contentValues,
            "dbAccount=? and dbPwd=? and dbId=?",
            arrayOf(mDbAccount, mDbPwd, "$i")
        )
        writableDatabase.close()
        return update > 0
    }

    override fun update(str: String, str2: String): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(GroupDbBean.GroupTable.dbGroupName, str)
        val update = writableDatabase.update(
            GroupDbBean.TableName1,
            contentValues,
            "dbAccount=? and dbPwd=? and dbGroupName=?",
            arrayOf(mDbAccount, mDbPwd, str2)
        )
        writableDatabase.close()
        return update > 0
    }

    override fun update(str: String, i: Int): Boolean {
        val writableDatabase = mHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(GroupDbBean.GroupTable.dbId, i)
        val update = writableDatabase.update(
            GroupDbBean.TableName1,
            contentValues,
            "dbAccount=? and dbPwd=? and dbDevName=?",
            arrayOf(mDbAccount, mDbPwd, str)
        )
        writableDatabase.close()
        return update > 0
    }

    override fun query(str: String, i: Int): Boolean =
        queryIsEmpty("dbAccount=? and dbPwd=? and dbGroupName=? and dbFlag=?", arrayOf(mDbAccount, mDbPwd, str, "$i"))

    override fun queryDevNameByDevId(): String {
        val query = mHelper.readableDatabase.query(
            GroupDbBean.TableName1,
            null,
            "dbAccount=? and dbPwd=? and dbId=? ",
            arrayOf(mDbAccount, mDbPwd, "-1"),
            null,
            null,
            "id asc"
        )
        return if (query == null || query.count == 0 || !query.moveToNext()) "" else query.getString(1)
    }

    override fun query(str: String): Boolean =
        queryIsEmpty("dbAccount=? and dbPwd=? and dbGroupName=? ", arrayOf(mDbAccount, mDbPwd, str))

    override fun getChCount(str: String, i: Int): Int {
        val readableDatabase = mHelper.readableDatabase
        val query = readableDatabase.query(
            GroupDbBean.TableName1,
            null,
            "dbAccount=? and dbPwd=? and dbGroupName=? and dbId=? ",
            arrayOf(mDbAccount, mDbPwd, str, "$i"),
            null,
            null,
            "id asc"
        )
        if (query != null) {
            return query.count
        }
        return -1
    }

    override fun queryByGroupAndDevName(str: String, str2: String, i: Int): Boolean =
        queryIsEmpty(
            "dbAccount=? and dbPwd=? and dbGroupName=? and dbDevName=? and dbChannel=? ",
            arrayOf(mDbAccount, mDbPwd, str, str2, "$i")
        )

    override fun query(str: String, i: Int, str2: String, i2: Int): Boolean =
        queryIsEmpty(
            "dbAccount=? and dbPwd=? and dbGroupName=? and dbId=? and dbDevName=? and dbChannel=? ",
            arrayOf(mDbAccount, mDbPwd, str, "$i", str2, "$i2")
        )

    override fun queryDevName(str: String): Boolean =
        queryIsEmpty("dbAccount=? and dbPwd=? and dbDevName=?", arrayOf(mDbAccount, mDbPwd, str))

    override fun getGroupDatasByName(str: String): List<GroupBean> {
        val arrayList = ArrayList<GroupBean>()
        val readableDatabase = mHelper.readableDatabase
        val rawQuery = readableDatabase.rawQuery("select * from GroupForServer where dbGroupName = ? ", arrayOf(str))
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                val groupBean = rawQuery.toGroupBean()
                if (mDbAccount == groupBean.dbAccont && mDbPwd == groupBean.dbPwd) {
                    arrayList.add(groupBean)
                }
            }
            rawQuery.close()
        }
        readableDatabase.close()
        return arrayList
    }

    override fun getGroupDatas(): List<GroupBean> {
        val arrayList = ArrayList<GroupBean>()
        val readableDatabase = mHelper.readableDatabase
        val rawQuery = readableDatabase.rawQuery("select * from GroupForServer", null)
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                val groupBean = rawQuery.toGroupBean()
                if (mDbAccount == groupBean.dbAccont && mDbPwd == groupBean.dbPwd) {
                    arrayList.add(groupBean)
                }
            }
            rawQuery.close()
        }
        readableDatabase.close()
        return arrayList
    }

    override fun isEmpty(): Boolean {
        val arrayList = ArrayList<GroupBean>()
        val readableDatabase = mHelper.readableDatabase
        val rawQuery = readableDatabase.rawQuery("select * from GroupForServer", null)
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                val groupBean = rawQuery.toGroupBean()
                if (mDbAccount == groupBean.dbAccont && mDbPwd == groupBean.dbPwd) {
                    arrayList.add(groupBean)
                }
            }
            rawQuery.close()
        }
        readableDatabase.close()
        return arrayList.size <= 0
    }

    private fun queryIsEmpty(selection: String, selectionArgs: Array<String>): Boolean {
        val readableDatabase = mHelper.readableDatabase
        val query = readableDatabase.query(GroupDbBean.TableName1, null, selection, selectionArgs, null, null, "id asc")
        if (query == null || query.count != 0) {
            readableDatabase.close()
            return false
        }
        query.close()
        readableDatabase.close()
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
