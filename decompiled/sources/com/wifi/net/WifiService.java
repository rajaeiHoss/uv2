package com.wifi.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.streamax.Configs;
import com.wifi.net.listener.OnWifiConnectListener;
import com.wifi.net.listener.OnWifiEnabledListener;
import com.wifi.net.listener.OnWifiScanResultsListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WifiService {
    private static final int SCAN_RESULTS_UPDATED = 2;
    private static final String TAG = "WifiService";
    private static final int WIFI_CONNECT_FAILURE = 4;
    private static final int WIFI_CONNECT_SUCCESS = 3;
    private static final int WIFI_STATE_DISABLED = 1;
    private static final int WIFI_STATE_ENABLED = 0;
    /* access modifiers changed from: private */
    public static CallBackHandler mCallBackHandler = new CallBackHandler((AnonymousClass1) null);
    private static ConnectivityManager mConnectivityManager;
    /* access modifiers changed from: private */
    public static OnWifiConnectListener mOnWifiConnectListener;
    /* access modifiers changed from: private */
    public static OnWifiEnabledListener mOnWifiEnabledListener;
    /* access modifiers changed from: private */
    public static OnWifiScanResultsListener mOnWifiScanResultsListener;
    private static WifiManager mWifiManager;
    private static WifiBroadcastReceiver mWifiReceiver;
    private static WifiService mWifiService;

    private WifiService(Context context) {
        mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Configs.Key.WifiStatus);
        mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public static WifiService getInstance(Context context) {
        if (mWifiService == null) {
            synchronized (WifiService.class) {
                if (mWifiService == null) {
                    mWifiService = new WifiService(context);
                    mWifiReceiver = new WifiBroadcastReceiver();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                    intentFilter.addAction("android.net.wifi.STATE_CHANGE");
                    intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
                    intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
                    context.registerReceiver(mWifiReceiver, intentFilter);
                }
            }
        }
        return mWifiService;
    }

    public boolean isWifiEnabled() {
        WifiManager wifiManager = mWifiManager;
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    public void openWiFi() {
        WifiManager wifiManager;
        if (!isWifiEnabled() && (wifiManager = mWifiManager) != null) {
            wifiManager.setWifiEnabled(true);
        }
    }

    public void closeWiFi() {
        WifiManager wifiManager;
        if (isWifiEnabled() && (wifiManager = mWifiManager) != null) {
            wifiManager.setWifiEnabled(false);
        }
    }

    public WifiInfo getCurrentWifi() {
        WifiManager wifiManager = mWifiManager;
        if (wifiManager != null) {
            return wifiManager.getConnectionInfo();
        }
        return null;
    }

    public void startScan() {
        WifiManager wifiManager = mWifiManager;
        if (wifiManager != null) {
            wifiManager.startScan();
        }
    }

    public List<ScanResult> getScanResults() {
        WifiManager wifiManager = mWifiManager;
        if (wifiManager != null) {
            return wifiManager.getScanResults();
        }
        return null;
    }

    public static ArrayList<ScanResult> excludeRepetition(List<ScanResult> list) {
        HashMap<String, ScanResult> hashMap = new HashMap<>();
        for (ScanResult next : list) {
            String str = next.SSID;
            if (!TextUtils.isEmpty(str)) {
                ScanResult scanResult = hashMap.get(str);
                if (scanResult == null) {
                    hashMap.put(str, next);
                } else if (WifiManager.calculateSignalLevel(scanResult.level, 100) < WifiManager.calculateSignalLevel(next.level, 100)) {
                    hashMap.put(str, next);
                }
            }
        }
        ArrayList<ScanResult> arrayList = new ArrayList<>();
        for (Map.Entry<String, ScanResult> value : hashMap.entrySet()) {
            arrayList.add(value.getValue());
        }
        return arrayList;
    }

    private List<WifiConfiguration> getConfiguredNetworks() {
        WifiManager wifiManager = mWifiManager;
        if (wifiManager != null) {
            return wifiManager.getConfiguredNetworks();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean saveConfiguration() {
        WifiManager wifiManager = mWifiManager;
        return wifiManager != null && wifiManager.saveConfiguration();
    }

    /* access modifiers changed from: package-private */
    public boolean enableNetwork(int i) {
        if (mWifiManager == null) {
            return false;
        }
        boolean disconnectCurrentWifi = disconnectCurrentWifi();
        boolean enableNetwork = mWifiManager.enableNetwork(i, true);
        boolean saveConfiguration = mWifiManager.saveConfiguration();
        boolean reconnect = mWifiManager.reconnect();
        if (!disconnectCurrentWifi || !enableNetwork || !saveConfiguration || !reconnect) {
            return false;
        }
        return true;
    }

    public boolean disconnectWifi(int i) {
        WifiManager wifiManager = mWifiManager;
        if (wifiManager == null) {
            return false;
        }
        boolean disableNetwork = wifiManager.disableNetwork(i);
        boolean disconnect = mWifiManager.disconnect();
        if (!disableNetwork || !disconnect) {
            return false;
        }
        return true;
    }

    public boolean disconnectCurrentWifi() {
        WifiInfo currentWifi = getCurrentWifi();
        if (currentWifi != null) {
            return disconnectWifi(currentWifi.getNetworkId());
        }
        return true;
    }

    public boolean deleteConfig(int i) {
        WifiManager wifiManager = mWifiManager;
        if (wifiManager == null) {
            return false;
        }
        boolean disableNetwork = wifiManager.disableNetwork(i);
        boolean removeNetwork = mWifiManager.removeNetwork(i);
        boolean saveConfiguration = mWifiManager.saveConfiguration();
        if (!disableNetwork || !removeNetwork || !saveConfiguration) {
            return false;
        }
        return true;
    }

    public int calculateSignalLevel(int i) {
        return WifiManager.calculateSignalLevel(i, 5);
    }

    public SecurityMode getSecurityMode(ScanResult scanResult) {
        String str = scanResult.capabilities;
        if (str.contains("WEP")) {
            return SecurityMode.WEP;
        }
        if (str.contains("WPA")) {
            return SecurityMode.WPA;
        }
        if (str.contains("WPA2")) {
            return SecurityMode.WPA2;
        }
        return SecurityMode.OPEN;
    }

    public String addDoubleQuotation(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return "\"" + str + "\"";
    }

    private int addNetwork(WifiConfiguration wifiConfiguration) {
        int addNetwork;
        WifiManager wifiManager = mWifiManager;
        if (wifiManager == null || -1 == (addNetwork = wifiManager.addNetwork(wifiConfiguration)) || !mWifiManager.saveConfiguration()) {
            return -1;
        }
        return addNetwork;
    }

    private int updateNetwork(WifiConfiguration wifiConfiguration) {
        int updateNetwork;
        WifiManager wifiManager = mWifiManager;
        if (wifiManager == null || -1 == (updateNetwork = wifiManager.updateNetwork(wifiConfiguration)) || !mWifiManager.saveConfiguration()) {
            return -1;
        }
        return updateNetwork;
    }

    public WifiConfiguration getConfigFromConfiguredNetworksBySsid(String str) {
        String addDoubleQuotation = addDoubleQuotation(str);
        List<WifiConfiguration> configuredNetworks = getConfiguredNetworks();
        if (configuredNetworks == null) {
            return null;
        }
        for (WifiConfiguration next : configuredNetworks) {
            if (next.SSID.equals(addDoubleQuotation)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int setOpenNetwork(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        WifiConfiguration configFromConfiguredNetworksBySsid = getConfigFromConfiguredNetworksBySsid(str);
        if (configFromConfiguredNetworksBySsid != null) {
            return configFromConfiguredNetworksBySsid.networkId;
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = addDoubleQuotation(str);
        wifiConfiguration.allowedKeyManagement.set(0);
        wifiConfiguration.allowedProtocols.set(1);
        wifiConfiguration.allowedProtocols.set(0);
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        wifiConfiguration.allowedPairwiseCiphers.set(1);
        wifiConfiguration.allowedGroupCiphers.set(0);
        wifiConfiguration.allowedGroupCiphers.set(1);
        wifiConfiguration.allowedGroupCiphers.set(3);
        wifiConfiguration.allowedGroupCiphers.set(2);
        return addNetwork(wifiConfiguration);
    }

    /* access modifiers changed from: package-private */
    public int setWEPNetwork(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        WifiConfiguration configFromConfiguredNetworksBySsid = getConfigFromConfiguredNetworksBySsid(str);
        if (configFromConfiguredNetworksBySsid == null) {
            WifiConfiguration wifiConfiguration = new WifiConfiguration();
            wifiConfiguration.SSID = addDoubleQuotation(str);
            String[] strArr = wifiConfiguration.wepKeys;
            strArr[0] = "\"" + str2 + "\"";
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.allowedProtocols.set(1);
            wifiConfiguration.allowedProtocols.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            return addNetwork(wifiConfiguration);
        }
        String[] strArr2 = configFromConfiguredNetworksBySsid.wepKeys;
        strArr2[0] = "\"" + str2 + "\"";
        return updateNetwork(configFromConfiguredNetworksBySsid);
    }

    /* access modifiers changed from: package-private */
    public int setWPA2Network(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        WifiConfiguration configFromConfiguredNetworksBySsid = getConfigFromConfiguredNetworksBySsid(str);
        if (configFromConfiguredNetworksBySsid == null) {
            WifiConfiguration wifiConfiguration = new WifiConfiguration();
            wifiConfiguration.SSID = addDoubleQuotation(str);
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.allowedProtocols.set(1);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.status = 2;
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedProtocols.set(1);
            wifiConfiguration.allowedProtocols.set(0);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            return addNetwork(wifiConfiguration);
        }
        configFromConfiguredNetworksBySsid.preSharedKey = "\"" + str2 + "\"";
        return updateNetwork(configFromConfiguredNetworksBySsid);
    }

    public boolean connectOpenNetwork(String str) {
        int openNetwork = setOpenNetwork(str);
        if (-1 == openNetwork) {
            return false;
        }
        boolean saveConfiguration = saveConfiguration();
        boolean enableNetwork = enableNetwork(openNetwork);
        if (!saveConfiguration || !enableNetwork) {
            return false;
        }
        return true;
    }

    public boolean connectWEPNetwork(String str, String str2) {
        int wEPNetwork = setWEPNetwork(str, str2);
        if (-1 == wEPNetwork) {
            return false;
        }
        boolean saveConfiguration = saveConfiguration();
        boolean enableNetwork = enableNetwork(wEPNetwork);
        if (!saveConfiguration || !enableNetwork) {
            return false;
        }
        return true;
    }

    public boolean connectWPA2Network(String str, String str2) {
        int wPA2Network = setWPA2Network(str, str2);
        if (-1 == wPA2Network) {
            return false;
        }
        boolean saveConfiguration = saveConfiguration();
        boolean enableNetwork = enableNetwork(wPA2Network);
        if (!saveConfiguration || !enableNetwork) {
            return false;
        }
        return true;
    }

    public void setOnWifiEnabledListener(OnWifiEnabledListener onWifiEnabledListener) {
        mOnWifiEnabledListener = onWifiEnabledListener;
    }

    public void removeOnWifiEnabledListener() {
        mOnWifiEnabledListener = null;
    }

    public void setOnWifiScanResultsListener(OnWifiScanResultsListener onWifiScanResultsListener) {
        mOnWifiScanResultsListener = onWifiScanResultsListener;
    }

    public void removeOnWifiScanResultsListener() {
        mOnWifiScanResultsListener = null;
    }

    public void setOnWifiConnectListener(OnWifiConnectListener onWifiConnectListener) {
        mOnWifiConnectListener = onWifiConnectListener;
    }

    public void removeOnWifiConnectListener() {
        mOnWifiConnectListener = null;
    }

    public static class WifiBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Configs.Key.WifiStatus);
            String action = intent.getAction();
            action.hashCode();
            char c = 65535;
            switch (action.hashCode()) {
                case -1875733435:
                    if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                        c = 0;
                        break;
                    }
                    break;
                case 233521600:
                    if (action.equals("android.net.wifi.supplicant.STATE_CHANGE")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1878357501:
                    if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    int intExtra = intent.getIntExtra("wifi_state", 4);
                    if (intExtra == 1) {
                        Log.i(WifiService.TAG, "onReceive: WIFI closed");
                        WifiService.mCallBackHandler.sendEmptyMessage(1);
                        return;
                    } else if (intExtra != 3) {
                        Log.i(WifiService.TAG, "onReceive: WIFI unknown!");
                        return;
                    } else {
                        Log.i(WifiService.TAG, "onReceive: WIFI opened");
                        WifiService.mCallBackHandler.sendEmptyMessage(0);
                        return;
                    }
                case 1:
                    int i = AnonymousClass1.$SwitchMap$android$net$wifi$SupplicantState[((SupplicantState) intent.getParcelableExtra("newState")).ordinal()];
                    if (i == 1) {
                        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                        Log.i(WifiService.TAG, "onReceive: inactive connectFailureInfo = " + connectionInfo);
                        if (connectionInfo != null) {
                            Message obtain = Message.obtain();
                            obtain.what = 4;
                            obtain.obj = connectionInfo.getSSID();
                            WifiService.mCallBackHandler.sendMessage(obtain);
                            return;
                        }
                        return;
                    } else if (i == 2) {
                        Log.i(WifiService.TAG, "onReceive: WIFI_CONNECT_SUCCESS");
                        WifiInfo connectionInfo2 = wifiManager.getConnectionInfo();
                        if (connectionInfo2 != null) {
                            Message obtain2 = Message.obtain();
                            obtain2.what = 3;
                            obtain2.obj = connectionInfo2.getSSID();
                            WifiService.mCallBackHandler.sendMessage(obtain2);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                case 2:
                    if (Build.VERSION.SDK_INT >= 23) {
                        boolean booleanExtra = intent.getBooleanExtra("resultsUpdated", false);
                        StringBuilder sb = new StringBuilder();
                        sb.append("onReceive: WIFI scan ");
                        sb.append(booleanExtra ? "complete" : "incomplete");
                        Log.i(WifiService.TAG, sb.toString());
                    } else {
                        Log.i(WifiService.TAG, "onReceive: WIFI scan complete");
                    }
                    Message obtain3 = Message.obtain();
                    obtain3.what = 2;
                    obtain3.obj = wifiManager.getScanResults();
                    WifiService.mCallBackHandler.sendMessage(obtain3);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.wifi.net.WifiService$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$net$wifi$SupplicantState;

        static {
            $SwitchMap$android$net$wifi$SupplicantState = new int[SupplicantState.values().length];
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.COMPLETED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class CallBackHandler extends Handler {
        private CallBackHandler() {
        }

        /* synthetic */ CallBackHandler(AnonymousClass1 r1) {
            this();
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i == 4 && WifiService.mOnWifiConnectListener != null) {
                                WifiService.mOnWifiConnectListener.onWiFiConnectFailure((String) message.obj);
                            }
                        } else if (WifiService.mOnWifiConnectListener != null) {
                            WifiService.mOnWifiConnectListener.onWiFiConnectSuccess((String) message.obj);
                        }
                    } else if (WifiService.mOnWifiScanResultsListener != null) {
                        WifiService.mOnWifiScanResultsListener.onScanResults((List) message.obj);
                    }
                } else if (WifiService.mOnWifiEnabledListener != null) {
                    WifiService.mOnWifiEnabledListener.onWifiEnabled(false);
                }
            } else if (WifiService.mOnWifiEnabledListener != null) {
                WifiService.mOnWifiEnabledListener.onWifiEnabled(true);
            }
        }
    }
}
