package com.wifi.net

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.ScanResult
import android.net.wifi.SupplicantState
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.util.Log
import com.streamax.Configs
import com.wifi.net.listener.OnWifiConnectListener
import com.wifi.net.listener.OnWifiEnabledListener
import com.wifi.net.listener.OnWifiScanResultsListener

class WifiService private constructor(context: Context) {

    init {
        mWifiManager = context.applicationContext.getSystemService(Configs.Key.WifiStatus) as? WifiManager
        mConnectivityManager = context.getSystemService("connectivity") as? ConnectivityManager
    }

    fun isWifiEnabled(): Boolean {
        return mWifiManager?.isWifiEnabled == true
    }

    fun openWiFi() {
        if (!isWifiEnabled()) {
            mWifiManager?.setWifiEnabled(true)
        }
    }

    fun closeWiFi() {
        if (isWifiEnabled()) {
            mWifiManager?.setWifiEnabled(false)
        }
    }

    fun getCurrentWifi(): WifiInfo? {
        return mWifiManager?.connectionInfo
    }

    fun startScan() {
        mWifiManager?.startScan()
    }

    fun getScanResults(): List<ScanResult>? {
        return mWifiManager?.scanResults
    }

    private fun getConfiguredNetworks(): List<WifiConfiguration>? {
        return mWifiManager?.configuredNetworks
    }

    internal fun saveConfiguration(): Boolean {
        return mWifiManager?.saveConfiguration() == true
    }

    internal fun enableNetwork(networkId: Int): Boolean {
        val wifiManager = mWifiManager ?: return false
        val disconnectCurrentWifi = disconnectCurrentWifi()
        val enableNetwork = wifiManager.enableNetwork(networkId, true)
        val saveConfiguration = wifiManager.saveConfiguration()
        val reconnect = wifiManager.reconnect()
        return disconnectCurrentWifi && enableNetwork && saveConfiguration && reconnect
    }

    fun disconnectWifi(networkId: Int): Boolean {
        val wifiManager = mWifiManager ?: return false
        val disableNetwork = wifiManager.disableNetwork(networkId)
        val disconnect = wifiManager.disconnect()
        return disableNetwork && disconnect
    }

    fun disconnectCurrentWifi(): Boolean {
        val currentWifi = getCurrentWifi()
        return if (currentWifi != null) {
            disconnectWifi(currentWifi.networkId)
        } else {
            true
        }
    }

    fun deleteConfig(networkId: Int): Boolean {
        val wifiManager = mWifiManager ?: return false
        val disableNetwork = wifiManager.disableNetwork(networkId)
        val removeNetwork = wifiManager.removeNetwork(networkId)
        val saveConfiguration = wifiManager.saveConfiguration()
        return disableNetwork && removeNetwork && saveConfiguration
    }

    fun calculateSignalLevel(level: Int): Int {
        return WifiManager.calculateSignalLevel(level, 5)
    }

    fun getSecurityMode(scanResult: ScanResult): SecurityMode {
        val capabilities = scanResult.capabilities
        if (capabilities.contains("WEP")) {
            return SecurityMode.WEP
        }
        if (capabilities.contains("WPA")) {
            return SecurityMode.WPA
        }
        if (capabilities.contains("WPA2")) {
            return SecurityMode.WPA2
        }
        return SecurityMode.OPEN
    }

    fun addDoubleQuotation(str: String?): String {
        if (TextUtils.isEmpty(str)) {
            return ""
        }
        return "\"$str\""
    }

    private fun addNetwork(wifiConfiguration: WifiConfiguration): Int {
        val wifiManager = mWifiManager ?: return -1
        val networkId = wifiManager.addNetwork(wifiConfiguration)
        if (networkId == -1 || !wifiManager.saveConfiguration()) {
            return -1
        }
        return networkId
    }

    private fun updateNetwork(wifiConfiguration: WifiConfiguration): Int {
        val wifiManager = mWifiManager ?: return -1
        val networkId = wifiManager.updateNetwork(wifiConfiguration)
        if (networkId == -1 || !wifiManager.saveConfiguration()) {
            return -1
        }
        return networkId
    }

    fun getConfigFromConfiguredNetworksBySsid(ssid: String): WifiConfiguration? {
        val quotedSsid = addDoubleQuotation(ssid)
        val configuredNetworks = getConfiguredNetworks() ?: return null
        for (network in configuredNetworks) {
            if (network.SSID == quotedSsid) {
                return network
            }
        }
        return null
    }

    internal fun setOpenNetwork(ssid: String?): Int {
        if (TextUtils.isEmpty(ssid)) {
            return -1
        }
        val config = getConfigFromConfiguredNetworksBySsid(ssid!!)
        if (config != null) {
            return config.networkId
        }
        val wifiConfiguration = WifiConfiguration()
        wifiConfiguration.SSID = addDoubleQuotation(ssid)
        wifiConfiguration.allowedKeyManagement.set(0)
        wifiConfiguration.allowedProtocols.set(1)
        wifiConfiguration.allowedProtocols.set(0)
        wifiConfiguration.allowedAuthAlgorithms.clear()
        wifiConfiguration.allowedPairwiseCiphers.set(2)
        wifiConfiguration.allowedPairwiseCiphers.set(1)
        wifiConfiguration.allowedGroupCiphers.set(0)
        wifiConfiguration.allowedGroupCiphers.set(1)
        wifiConfiguration.allowedGroupCiphers.set(3)
        wifiConfiguration.allowedGroupCiphers.set(2)
        return addNetwork(wifiConfiguration)
    }

    internal fun setWEPNetwork(ssid: String?, password: String?): Int {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            return -1
        }
        val config = getConfigFromConfiguredNetworksBySsid(ssid!!)
        if (config == null) {
            val wifiConfiguration = WifiConfiguration()
            wifiConfiguration.SSID = addDoubleQuotation(ssid)
            wifiConfiguration.wepKeys[0] = "\"$password\""
            wifiConfiguration.allowedKeyManagement.set(0)
            wifiConfiguration.allowedProtocols.set(1)
            wifiConfiguration.allowedProtocols.set(0)
            wifiConfiguration.allowedAuthAlgorithms.set(0)
            wifiConfiguration.allowedAuthAlgorithms.set(1)
            wifiConfiguration.allowedPairwiseCiphers.set(2)
            wifiConfiguration.allowedPairwiseCiphers.set(1)
            wifiConfiguration.allowedGroupCiphers.set(0)
            wifiConfiguration.allowedGroupCiphers.set(1)
            return addNetwork(wifiConfiguration)
        }
        config.wepKeys[0] = "\"$password\""
        return updateNetwork(config)
    }

    internal fun setWPA2Network(ssid: String?, password: String?): Int {
        if (TextUtils.isEmpty(ssid) || TextUtils.isEmpty(password)) {
            return -1
        }
        val config = getConfigFromConfiguredNetworksBySsid(ssid!!)
        if (config == null) {
            val wifiConfiguration = WifiConfiguration()
            wifiConfiguration.SSID = addDoubleQuotation(ssid)
            wifiConfiguration.preSharedKey = "\"$password\""
            wifiConfiguration.allowedProtocols.set(1)
            wifiConfiguration.allowedKeyManagement.set(1)
            wifiConfiguration.status = 2
            wifiConfiguration.allowedGroupCiphers.set(2)
            wifiConfiguration.allowedGroupCiphers.set(3)
            wifiConfiguration.allowedPairwiseCiphers.set(1)
            wifiConfiguration.allowedPairwiseCiphers.set(2)
            wifiConfiguration.allowedProtocols.set(1)
            wifiConfiguration.allowedProtocols.set(0)
            wifiConfiguration.allowedGroupCiphers.set(3)
            wifiConfiguration.allowedPairwiseCiphers.set(2)
            return addNetwork(wifiConfiguration)
        }
        config.preSharedKey = "\"$password\""
        return updateNetwork(config)
    }

    fun connectOpenNetwork(ssid: String?): Boolean {
        val networkId = setOpenNetwork(ssid)
        if (networkId == -1) {
            return false
        }
        val saveConfiguration = saveConfiguration()
        val enableNetwork = enableNetwork(networkId)
        return saveConfiguration && enableNetwork
    }

    fun connectWEPNetwork(ssid: String?, password: String?): Boolean {
        val networkId = setWEPNetwork(ssid, password)
        if (networkId == -1) {
            return false
        }
        val saveConfiguration = saveConfiguration()
        val enableNetwork = enableNetwork(networkId)
        return saveConfiguration && enableNetwork
    }

    fun connectWPA2Network(ssid: String?, password: String?): Boolean {
        val networkId = setWPA2Network(ssid, password)
        if (networkId == -1) {
            return false
        }
        val saveConfiguration = saveConfiguration()
        val enableNetwork = enableNetwork(networkId)
        return saveConfiguration && enableNetwork
    }

    fun setOnWifiEnabledListener(onWifiEnabledListener: OnWifiEnabledListener?) {
        mOnWifiEnabledListener = onWifiEnabledListener
    }

    fun removeOnWifiEnabledListener() {
        mOnWifiEnabledListener = null
    }

    fun setOnWifiScanResultsListener(onWifiScanResultsListener: OnWifiScanResultsListener?) {
        mOnWifiScanResultsListener = onWifiScanResultsListener
    }

    fun removeOnWifiScanResultsListener() {
        mOnWifiScanResultsListener = null
    }

    fun setOnWifiConnectListener(onWifiConnectListener: OnWifiConnectListener?) {
        mOnWifiConnectListener = onWifiConnectListener
    }

    fun removeOnWifiConnectListener() {
        mOnWifiConnectListener = null
    }

    class WifiBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val wifiManager = context.applicationContext.getSystemService(Configs.Key.WifiStatus) as? WifiManager ?: return
            when (intent.action) {
                "android.net.wifi.WIFI_STATE_CHANGED" -> {
                    when (intent.getIntExtra("wifi_state", 4)) {
                        1 -> {
                            Log.i(TAG, "onReceive: WIFI closed")
                            mCallBackHandler.sendEmptyMessage(WIFI_STATE_DISABLED)
                        }

                        3 -> {
                            Log.i(TAG, "onReceive: WIFI opened")
                            mCallBackHandler.sendEmptyMessage(WIFI_STATE_ENABLED)
                        }

                        else -> {
                            Log.i(TAG, "onReceive: WIFI unknown!")
                        }
                    }
                }

                "android.net.wifi.supplicant.STATE_CHANGE" -> {
                    val newState = intent.getParcelableExtra("newState") as? SupplicantState ?: return
                    when (newState) {
                        SupplicantState.INACTIVE -> {
                            val connectionInfo = wifiManager.connectionInfo
                            Log.i(TAG, "onReceive: inactive connectFailureInfo = $connectionInfo")
                            if (connectionInfo != null) {
                                val obtain = Message.obtain()
                                obtain.what = WIFI_CONNECT_FAILURE
                                obtain.obj = connectionInfo.ssid
                                mCallBackHandler.sendMessage(obtain)
                            }
                        }

                        SupplicantState.COMPLETED -> {
                            Log.i(TAG, "onReceive: WIFI_CONNECT_SUCCESS")
                            val connectionInfo = wifiManager.connectionInfo
                            if (connectionInfo != null) {
                                val obtain = Message.obtain()
                                obtain.what = WIFI_CONNECT_SUCCESS
                                obtain.obj = connectionInfo.ssid
                                mCallBackHandler.sendMessage(obtain)
                            }
                        }

                        else -> {
                        }
                    }
                }

                "android.net.wifi.SCAN_RESULTS" -> {
                    if (Build.VERSION.SDK_INT >= 23) {
                        val resultsUpdated = intent.getBooleanExtra("resultsUpdated", false)
                        Log.i(TAG, "onReceive: WIFI scan " + if (resultsUpdated) "complete" else "incomplete")
                    } else {
                        Log.i(TAG, "onReceive: WIFI scan complete")
                    }
                    val obtain = Message.obtain()
                    obtain.what = SCAN_RESULTS_UPDATED
                    obtain.obj = wifiManager.scanResults
                    mCallBackHandler.sendMessage(obtain)
                }
            }
        }
    }

    private class CallBackHandler : Handler() {
        override fun handleMessage(message: Message) {
            super.handleMessage(message)
            when (message.what) {
                WIFI_STATE_ENABLED -> mOnWifiEnabledListener?.onWifiEnabled(true)
                WIFI_STATE_DISABLED -> mOnWifiEnabledListener?.onWifiEnabled(false)
                SCAN_RESULTS_UPDATED -> {
                    val rawResults = message.obj as? List<*> ?: return
                    val scanResults = rawResults.filterIsInstance<ScanResult>()
                    mOnWifiScanResultsListener?.onScanResults(scanResults)
                }

                WIFI_CONNECT_SUCCESS -> {
                    val ssid = message.obj as? String
                    if (ssid != null) {
                        mOnWifiConnectListener?.onWiFiConnectSuccess(ssid)
                    }
                }

                WIFI_CONNECT_FAILURE -> {
                    val ssid = message.obj as? String
                    if (ssid != null) {
                        mOnWifiConnectListener?.onWiFiConnectFailure(ssid)
                    }
                }
            }
        }
    }

    companion object {
        private const val SCAN_RESULTS_UPDATED = 2
        private const val TAG = "WifiService"
        private const val WIFI_CONNECT_FAILURE = 4
        private const val WIFI_CONNECT_SUCCESS = 3
        private const val WIFI_STATE_DISABLED = 1
        private const val WIFI_STATE_ENABLED = 0

        private val mCallBackHandler: CallBackHandler = CallBackHandler()
        private var mConnectivityManager: ConnectivityManager? = null
        private var mOnWifiConnectListener: OnWifiConnectListener? = null
        private var mOnWifiEnabledListener: OnWifiEnabledListener? = null
        private var mOnWifiScanResultsListener: OnWifiScanResultsListener? = null
        private var mWifiManager: WifiManager? = null
        private var mWifiReceiver: WifiBroadcastReceiver? = null
        private var mWifiService: WifiService? = null

        @JvmStatic
        fun getInstance(context: Context): WifiService {
            if (mWifiService == null) {
                synchronized(WifiService::class.java) {
                    if (mWifiService == null) {
                        mWifiService = WifiService(context)
                        val receiver = WifiBroadcastReceiver()
                        mWifiReceiver = receiver
                        val intentFilter = IntentFilter()
                        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED")
                        intentFilter.addAction("android.net.wifi.STATE_CHANGE")
                        intentFilter.addAction("android.net.wifi.SCAN_RESULTS")
                        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE")
                        context.registerReceiver(receiver, intentFilter)
                    }
                }
            }
            return mWifiService!!
        }

        @JvmStatic
        fun excludeRepetition(list: List<ScanResult>): ArrayList<ScanResult> {
            val scanResultMap = HashMap<String, ScanResult>()
            for (scanResult in list) {
                val ssid = scanResult.SSID
                if (!TextUtils.isEmpty(ssid)) {
                    val lastScanResult = scanResultMap[ssid]
                    if (lastScanResult == null) {
                        scanResultMap[ssid] = scanResult
                    } else if (WifiManager.calculateSignalLevel(lastScanResult.level, 100) < WifiManager.calculateSignalLevel(scanResult.level, 100)) {
                        scanResultMap[ssid] = scanResult
                    }
                }
            }
            val result = ArrayList<ScanResult>()
            for (entry in scanResultMap.entries) {
                result.add(entry.value)
            }
            return result
        }
    }
}
