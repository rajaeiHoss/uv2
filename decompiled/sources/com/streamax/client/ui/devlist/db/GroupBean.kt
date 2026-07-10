package com.streamax.client.ui.devlist.db

class GroupBean {
    @JvmField var dbAccont: String? = null
    @JvmField var dbChannel: Int = 0
    @JvmField var dbDevName: String? = null
    @JvmField var dbFlag: Int = 0
    @JvmField var dbGroupName: String? = null
    @JvmField var dbId: Int = 0
    @JvmField var dbPwd: String? = null

    override fun toString(): String =
        "DevGroupBean_dbGroupName:$dbGroupName,dbDevName:$dbDevName,dbChannel:$dbChannel,dbId:$dbId,dbFlag:$dbFlag"
}
