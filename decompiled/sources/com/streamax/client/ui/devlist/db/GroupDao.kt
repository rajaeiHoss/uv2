package com.streamax.client.ui.devlist.db

abstract class GroupDao {
    abstract fun add(str: String, i: Int, str2: String, i2: Int, i3: Int): Boolean

    abstract fun delete(i: Int): Boolean

    abstract fun delete(i: Int, i2: Int): Boolean

    abstract fun delete(str: String): Boolean

    abstract fun delete(str: String, i: Int, str2: String, i2: Int): Boolean

    abstract fun deleteDb()

    abstract fun getChCount(str: String, i: Int): Int

    abstract fun getGroupDatas(): List<GroupBean>

    abstract fun getGroupDatasByName(str: String): List<GroupBean>

    abstract fun isEmpty(): Boolean

    abstract fun query(str: String): Boolean

    abstract fun query(str: String, i: Int): Boolean

    abstract fun query(str: String, i: Int, str2: String, i2: Int): Boolean

    abstract fun queryByGroupAndDevName(str: String, str2: String, i: Int): Boolean

    abstract fun queryDevName(str: String): Boolean

    abstract fun queryDevNameByDevId(): String

    abstract fun update(str: String, i: Int): Boolean

    abstract fun update(str: String, i: Int, str2: String, i2: Int, i3: Int): Boolean

    abstract fun update(str: String, str2: String): Boolean

    abstract fun updateDevNameById(i: Int, str: String): Boolean
}
