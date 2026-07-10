package com.streamax.config.db

interface DatebaseConfig {
    companion object {
        const val DatabaseName: String = "DeviceInfo.db"
        const val TableName: String = "DeviceInfo"
    }

    interface DeviceInfoTable {
        companion object {
            const val dbCurrentChannel: String = "dbCurrentChannel"
            const val dbCurrentDay: String = "dbCurrentDay"
            const val dbCurrentPlan: String = "dbCurrentPlan"
            const val dbCurrentTotalChannel: String = "dbCurrentTotalChannel"
            const val dbCurrentTotalPlan: String = "dbCurrentTotalPlan"
            const val dbDeviceIp: String = "dbDeviceIp"
        }
    }
}
