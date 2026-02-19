package com.wifi.net.listener;

import android.net.wifi.ScanResult;
import java.util.List;

public interface OnWifiScanResultsListener {
    void onScanResults(List<ScanResult> list);
}
