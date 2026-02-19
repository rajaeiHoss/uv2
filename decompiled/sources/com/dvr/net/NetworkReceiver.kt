package com.dvr.net

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.dvr.common.NetWorkUtils

class NetworkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "[onReceive]$intent")
        Log.v(TAG, "网络状态改变,进入onReceive方法")
        val connectivityManager = context.getSystemService("connectivity") as ConnectivityManager
        var status = STATUS_GPRS
        var wifiState: NetworkInfo.State? = null
        var gprsState: NetworkInfo.State? = null
        try {
            wifiState = connectivityManager.getNetworkInfo(1)?.state
        } catch (_: Exception) {
            Log.v(TAG, "测试机没有WIFI模块")
        }
        try {
            gprsState = connectivityManager.getNetworkInfo(0)?.state
        } catch (_: Exception) {
            Log.v(TAG, "测试机没有GPRS模块")
        }

        if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
            status = STATUS_WIFI
            Log.v(TAG, "mStatus=$mStatus 改变后的网络为WIFI")
            val localIpAddress = NetWorkUtils.getLocalIpAddress(context)
            DvrNet.SetLocalIp(localIpAddress)
            Log.v(TAG, "wifiip =$localIpAddress")
        } else if (gprsState == null || NetworkInfo.State.CONNECTED != gprsState) {
            Log.v(TAG, "mStatus=$mStatus 改变后的网络为ERROR")
            status = STATUS_ERROR
        } else {
            Log.v(TAG, "mStatus=$mStatus 改变后的网络为GPRS")
            val ipAddress = NetWorkUtils.getIpAddress()
            DvrNet.SetLocalIp(ipAddress)
            Log.v(TAG, "gprsIp =$ipAddress")
        }

        if (mStatus != status) {
            Log.v(TAG, "mStatus与改变后的网络不同，网络真的改变了")
        } else {
            Log.v(TAG, "mStatus与改变后的网络相同，不处理")
        }
        mStatus = status
    }

    companion object {
        private const val STATUS_ERROR = 0
        private const val STATUS_GPRS = 1
        private const val STATUS_WIFI = 2
        private const val TAG = "NetworkReceiver"

        @JvmField
        var mStatus: Int = STATUS_WIFI
    }
}
