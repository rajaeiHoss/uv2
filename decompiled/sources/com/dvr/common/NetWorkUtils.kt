package com.dvr.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import com.streamax.Configs
import java.net.Inet4Address
import java.net.NetworkInterface

object NetWorkUtils {
    @JvmStatic
    fun checkEnable(context: Context): Boolean {
        val manager = context.getSystemService("connectivity") as ConnectivityManager?
        val activeNetworkInfo = manager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable
    }

    @JvmStatic
    fun getLocalIpAddress(context: Context): String {
        val ipAddress = (context.getSystemService(Configs.Key.WifiStatus) as WifiManager)
            .connectionInfo
            .ipAddress
        return String.format(
            "%d.%d.%d.%d",
            ipAddress and 255,
            ipAddress shr 8 and 255,
            ipAddress shr 16 and 255,
            ipAddress shr 24 and 255
        )
    }

    @JvmStatic
    fun getIpAddress(): String? {
        return try {
            val networkInterfaces = NetworkInterface.getNetworkInterfaces()
            while (networkInterfaces.hasMoreElements()) {
                val inetAddresses = networkInterfaces.nextElement().inetAddresses
                while (inetAddresses.hasMoreElements()) {
                    val nextElement = inetAddresses.nextElement()
                    if (!nextElement.isLoopbackAddress && nextElement is Inet4Address) {
                        return nextElement.hostAddress
                    }
                }
            }
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
