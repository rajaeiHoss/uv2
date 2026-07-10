package com.streamax.client.ui.devlist.db

abstract class GroupDao {
    abstract fun add(groupName: String, deviceId: Int, deviceName: String, channel: Int, flag: Int): Boolean

    abstract fun delete(deviceId: Int): Boolean

    abstract fun delete(deviceId: Int, channel: Int): Boolean

    abstract fun delete(groupName: String): Boolean

    abstract fun delete(groupName: String, deviceId: Int, deviceName: String, channel: Int): Boolean

    abstract fun deleteDb()

    abstract fun getChCount(groupName: String, deviceId: Int): Int

    abstract fun getGroupDatas(): List<GroupBean>

    abstract fun getGroupDatasByName(groupName: String): List<GroupBean>

    abstract fun isEmpty(): Boolean

    abstract fun query(groupName: String): Boolean

    abstract fun query(groupName: String, flag: Int): Boolean

    abstract fun query(groupName: String, deviceId: Int, deviceName: String, channel: Int): Boolean

    abstract fun queryByGroupAndDevName(groupName: String, deviceName: String, channel: Int): Boolean

    abstract fun queryDevName(deviceName: String): Boolean

    abstract fun queryDevNameByDevId(): String

    abstract fun update(deviceName: String, deviceId: Int): Boolean

    abstract fun update(groupName: String, deviceId: Int, deviceName: String, channel: Int, flag: Int): Boolean

    abstract fun update(newGroupName: String, oldGroupName: String): Boolean

    abstract fun updateDevNameById(deviceId: Int, deviceName: String): Boolean
}
