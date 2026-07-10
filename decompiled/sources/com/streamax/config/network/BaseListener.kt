package com.streamax.config.network

import com.streamax.config.base.BaseFragment

interface BaseListener {
    fun getCurFragment(): BaseFragment

    interface GetListener : BaseListener {
        fun getFailure()

        fun getSuccess(config: String)

        fun requestForGetConfig(): String
    }

    interface GetStorageInfoListener : BaseListener {
        fun getFailure()

        fun getSuccess(storageInfo: String)
    }

    interface RebootListener : BaseListener {
        fun rebootFailure()

        fun rebootSuccess()
    }

    interface SetListener : BaseListener {
        fun requestForSetConfig(): String

        fun setFailure()

        fun setSuccess()
    }
}
