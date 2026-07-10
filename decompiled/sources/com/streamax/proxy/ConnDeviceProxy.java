package com.streamax.proxy;

import android.text.TextUtils;
import com.dvr.net.DvrNet;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.utils.LogUtils;
import java.util.Map;
import java.util.regex.Pattern;

public class ConnDeviceProxy {
    private static final String ERROR_CODE_KEY = "errorcode";
    private static final String TAG = "ConnDeviceProxy";

    public static boolean isIPAddr(String address) {
        if (address.length() < 7 || address.length() > 15 || "".equals(address) || !Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$").matcher(address).matches()) {
            return false;
        }
        return true;
    }

    public static boolean isDomain(String domain) {
        if (!"".equals(domain) && domain.split("\\.").length > 1) {
            return true;
        }
        return false;
    }

    public static Map<String, Object> connDeviceProxy(DvrNet dvrNet, DevInfoBean devInfoBean, MyApp myApp) {
        if (!devInfoBean.mLinkMode.isEmpty()) {
            return connDeviceByMode(dvrNet, devInfoBean, myApp);
        }
        return connDeviceByAuto(dvrNet, devInfoBean, myApp);
    }

    public static Map<String, Object> connDeviceByMode(DvrNet dvrNet, DevInfoBean devInfoBean, MyApp myApp) {
        if (devInfoBean.mLinkMode.equals("LAN")) {
            return connDeviceByC2C(dvrNet, devInfoBean, myApp);
        }
        if (devInfoBean.mLinkMode.equals("NAT")) {
            return connDeviceByNAT(dvrNet, devInfoBean, myApp);
        }
        if (devInfoBean.mLinkMode.equals("MSG")) {
            return connDeviceByMSG(dvrNet, devInfoBean, myApp);
        }
        return null;
    }

    public static Map<String, Object> connDeviceByAuto(DvrNet dvrNet, DevInfoBean devInfoBean, MyApp myApp) {
        LogUtils.e(TAG, "connDeviceByAuto begin, mDevIp: " + devInfoBean.mDevIp + ", mRemark: " + devInfoBean.mRemark);
        Map<String, Object> result = null;
        int errorCode = -1;
        if (hasDirectAddress(devInfoBean)) {
            LogUtils.e(TAG, "connDeviceByAuto, direct , mDevIp: " + devInfoBean.mDevIp);
            result = connDeviceByC2C(dvrNet, devInfoBean, myApp);
            if (result != null) {
                errorCode = getErrorCode(result);
            }
            if (MyApp.loginType == 0 || errorCode == 0) {
                return result;
            }
        }
        String deviceIdentity = MyApp.loginType == 0 ? devInfoBean.mDevIp : devInfoBean.mRemark;
        if (errorCode == -1) {
            LogUtils.e(TAG, "connDeviceByAuto nat, devInfo: " + deviceIdentity);
            result = connDeviceByNAT(dvrNet, devInfoBean, myApp);
        }
        if (result != null) {
            errorCode = getErrorCode(result);
        }
        if (errorCode == -1) {
            LogUtils.e(TAG, "connDeviceByAuto msg, devInfo: " + deviceIdentity);
            result = connDeviceByMSG(dvrNet, devInfoBean, myApp);
        }
        LogUtils.e(TAG, "connDeviceByAuto end, devInfo: " + deviceIdentity);
        return result;
    }

    private static boolean hasDirectAddress(DevInfoBean devInfoBean) {
        return devInfoBean.mDevIp != null && devInfoBean.mMediaPort > 0 && (isIPAddr(devInfoBean.mDevIp) || isDomain(devInfoBean.mDevIp));
    }

    private static int getErrorCode(Map<String, Object> result) {
        return ((Integer) result.get(ERROR_CODE_KEY)).intValue();
    }

    public static Map<String, Object> connDeviceByC2C(DvrNet dvrNet, DevInfoBean devInfoBean, MyApp myApp) {
        if (TextUtils.isEmpty(devInfoBean.mDevIp) || devInfoBean.mMediaPort <= 0 || TextUtils.isEmpty(devInfoBean.mUsername)) {
            return null;
        }
        MyApp.CONNTYPES = 0;
        return dvrNet.GetDeviceHandle(devInfoBean.mDevIp, devInfoBean.mMediaPort, devInfoBean.mUsername, devInfoBean.mPwd, myApp.getLocalMacAddress());
    }

    public static Map<String, Object> connDeviceByNAT(DvrNet dvrNet, DevInfoBean devInfoBean, MyApp myApp) {
        if (TextUtils.isEmpty(myApp.mUdtServerIp) || myApp.mUdtServerPort <= 0 || TextUtils.isEmpty(devInfoBean.mUsername)) {
            return null;
        }
        MyApp.CONNTYPES = 1;
        return dvrNet.GetDeviceHandle(myApp.mUdtServerIp, myApp.mUdtServerPort, myApp.getLocalMacAddress(), MyApp.loginType == 0 ? devInfoBean.mDevIp : devInfoBean.mRemark, devInfoBean.mUsername, devInfoBean.mPwd);
    }

    public static Map<String, Object> connDeviceByMSG(DvrNet dvrNet, DevInfoBean devInfoBean, MyApp myApp) {
        if (TextUtils.isEmpty(myApp.mMsgServerIp) || myApp.mMsgServerPort <= 0 || TextUtils.isEmpty(myApp.mGtServerIp)) {
            return null;
        }
        MyApp.CONNTYPES = 2;
        return dvrNet.GetDeviceHandle(myApp.mMsgServerIp, myApp.mMsgServerPort, myApp.mGtServerIp, 17891, 124, MyApp.loginType == 0 ? devInfoBean.mDevIp : devInfoBean.mRemark, devInfoBean.mUsername, devInfoBean.mPwd);
    }
}
