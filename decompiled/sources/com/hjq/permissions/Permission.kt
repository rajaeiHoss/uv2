package com.hjq.permissions

object Permission {
    const val ACCEPT_HANDOVER: String = "android.permission.ACCEPT_HANDOVER"
    const val ACCESS_BACKGROUND_LOCATION: String = "android.permission.ACCESS_BACKGROUND_LOCATION"
    const val ACCESS_COARSE_LOCATION: String = "android.permission.ACCESS_COARSE_LOCATION"
    const val ACCESS_FINE_LOCATION: String = "android.permission.ACCESS_FINE_LOCATION"
    const val ACCESS_MEDIA_LOCATION: String = "android.permission.ACCESS_MEDIA_LOCATION"
    const val ACCESS_NOTIFICATION_POLICY: String = "android.permission.ACCESS_NOTIFICATION_POLICY"
    const val ACTIVITY_RECOGNITION: String = "android.permission.ACTIVITY_RECOGNITION"
    const val ADD_VOICEMAIL: String = "com.android.voicemail.permission.ADD_VOICEMAIL"
    const val ANSWER_PHONE_CALLS: String = "android.permission.ANSWER_PHONE_CALLS"
    const val BIND_NOTIFICATION_LISTENER_SERVICE: String = "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE"
    const val BIND_VPN_SERVICE: String = "android.permission.BIND_VPN_SERVICE"
    const val BLUETOOTH_ADVERTISE: String = "android.permission.BLUETOOTH_ADVERTISE"
    const val BLUETOOTH_CONNECT: String = "android.permission.BLUETOOTH_CONNECT"
    const val BLUETOOTH_SCAN: String = "android.permission.BLUETOOTH_SCAN"
    const val BODY_SENSORS: String = "android.permission.BODY_SENSORS"
    const val BODY_SENSORS_BACKGROUND: String = "android.permission.BODY_SENSORS_BACKGROUND"
    const val CALL_PHONE: String = "android.permission.CALL_PHONE"
    const val CAMERA: String = "android.permission.CAMERA"
    const val GET_ACCOUNTS: String = "android.permission.GET_ACCOUNTS"
    const val MANAGE_EXTERNAL_STORAGE: String = "android.permission.MANAGE_EXTERNAL_STORAGE"
    const val NEARBY_WIFI_DEVICES: String = "android.permission.NEARBY_WIFI_DEVICES"
    const val NOTIFICATION_SERVICE: String = "android.permission.NOTIFICATION_SERVICE"
    const val PACKAGE_USAGE_STATS: String = "android.permission.PACKAGE_USAGE_STATS"
    const val POST_NOTIFICATIONS: String = "android.permission.POST_NOTIFICATIONS"
    const val PROCESS_OUTGOING_CALLS: String = "android.permission.PROCESS_OUTGOING_CALLS"
    const val READ_CALENDAR: String = "android.permission.READ_CALENDAR"
    const val READ_CALL_LOG: String = "android.permission.READ_CALL_LOG"
    const val READ_CONTACTS: String = "android.permission.READ_CONTACTS"
    const val READ_EXTERNAL_STORAGE: String = "android.permission.READ_EXTERNAL_STORAGE"
    const val READ_MEDIA_AUDIO: String = "android.permission.READ_MEDIA_AUDIO"
    const val READ_MEDIA_IMAGES: String = "android.permission.READ_MEDIA_IMAGES"
    const val READ_MEDIA_VIDEO: String = "android.permission.READ_MEDIA_VIDEO"
    const val READ_PHONE_NUMBERS: String = "android.permission.READ_PHONE_NUMBERS"
    const val READ_PHONE_STATE: String = "android.permission.READ_PHONE_STATE"
    const val READ_SMS: String = "android.permission.READ_SMS"
    const val RECEIVE_MMS: String = "android.permission.RECEIVE_MMS"
    const val RECEIVE_SMS: String = "android.permission.RECEIVE_SMS"
    const val RECEIVE_WAP_PUSH: String = "android.permission.RECEIVE_WAP_PUSH"
    const val RECORD_AUDIO: String = "android.permission.RECORD_AUDIO"
    const val REQUEST_IGNORE_BATTERY_OPTIMIZATIONS: String = "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"
    const val REQUEST_INSTALL_PACKAGES: String = "android.permission.REQUEST_INSTALL_PACKAGES"
    const val SCHEDULE_EXACT_ALARM: String = "android.permission.SCHEDULE_EXACT_ALARM"
    const val SEND_SMS: String = "android.permission.SEND_SMS"
    const val SYSTEM_ALERT_WINDOW: String = "android.permission.SYSTEM_ALERT_WINDOW"
    const val USE_SIP: String = "android.permission.USE_SIP"
    const val WRITE_CALENDAR: String = "android.permission.WRITE_CALENDAR"
    const val WRITE_CALL_LOG: String = "android.permission.WRITE_CALL_LOG"
    const val WRITE_CONTACTS: String = "android.permission.WRITE_CONTACTS"
    const val WRITE_EXTERNAL_STORAGE: String = "android.permission.WRITE_EXTERNAL_STORAGE"
    const val WRITE_SETTINGS: String = "android.permission.WRITE_SETTINGS"

    object Group {
        @JvmField
        val BLUETOOTH: Array<String> =
            arrayOf(BLUETOOTH_SCAN, BLUETOOTH_CONNECT, BLUETOOTH_ADVERTISE)

        @JvmField
        val CALENDAR: Array<String> =
            arrayOf(READ_CALENDAR, WRITE_CALENDAR)

        @JvmField
        val CONTACTS: Array<String> =
            arrayOf(READ_CONTACTS, WRITE_CONTACTS, GET_ACCOUNTS)

        @JvmField
        val STORAGE: Array<String> =
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
    }
}
