package com.streamax.client.ui.devlist.db

interface GroupDbBean {
    companion object {
        const val GroupDbName: String = "DevGroup.db"
        const val TableName0: String = "GroupForNormal"
        const val TableName1: String = "GroupForServer"
    }

    interface GroupTable {
        companion object {
            const val dbAccount: String = "dbAccount"
            const val dbChannel: String = "dbChannel"
            const val dbDevName: String = "dbDevName"
            const val dbFlag: String = "dbFlag"
            const val dbGroupName: String = "dbGroupName"
            const val dbId: String = "dbId"
            const val dbPwd: String = "dbPwd"
        }
    }
}
