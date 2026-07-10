package com.streamax.config.listener

interface SuperListener {
    interface GetConfigListener {
        fun onFailure()

        fun onSuccess(config: String)
    }

    interface LoadDeviceInfoListener {
        fun onFailure()

        fun onSuccess(deviceInfo: Any?)
    }

    interface SetConfigListener {
        fun onFailure()

        fun onSuccess()
    }

    interface SetRestoreDefaultListener {
        fun onSuccess()
    }
}
