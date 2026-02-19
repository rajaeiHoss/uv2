package com.wifi.net.listener

import android.net.wifi.ScanResult

interface OnWifiScanResultsListener {
    fun onScanResults(list: List<ScanResult>)
}
