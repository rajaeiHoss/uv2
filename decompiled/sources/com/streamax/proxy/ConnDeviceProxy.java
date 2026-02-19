package com.streamax.proxy;

import android.text.TextUtils;
import com.dvr.net.DvrNet;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import java.util.Map;
import java.util.regex.Pattern;

public class ConnDeviceProxy {
    public static boolean isIPAddr(String str) {
        if (str.length() < 7 || str.length() > 15 || "".equals(str) || !Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$").matcher(str).matches()) {
            return false;
        }
        return true;
    }

    public static boolean isDomain(String str) {
        if (!"".equals(str) && str.split("\\.").length > 1) {
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

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<java.lang.String, java.lang.Object> connDeviceByAuto(com.dvr.net.DvrNet r7, com.streamax.client.DevInfoBean r8, com.streamax.client.MyApp r9) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "connDeviceByAuto begin, mDevIp: "
            r0.append(r1)
            java.lang.String r1 = r8.mDevIp
            r0.append(r1)
            java.lang.String r1 = ", mRemark: "
            r0.append(r1)
            java.lang.String r1 = r8.mRemark
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ConnDeviceProxy"
            com.streamax.utils.LogUtils.e((java.lang.String) r1, (java.lang.String) r0)
            int r0 = com.streamax.client.MyApp.loginType
            java.lang.String r2 = "connDeviceByAuto, direct , mDevIp: "
            java.lang.String r3 = "errorcode"
            r4 = -1
            if (r0 != 0) goto L_0x0067
            java.lang.String r0 = r8.mDevIp
            if (r0 == 0) goto L_0x00a8
            int r0 = r8.mMediaPort
            if (r0 <= 0) goto L_0x00a8
            java.lang.String r0 = r8.mDevIp
            boolean r0 = isIPAddr(r0)
            if (r0 != 0) goto L_0x0043
            java.lang.String r0 = r8.mDevIp
            boolean r0 = isDomain(r0)
            if (r0 == 0) goto L_0x00a8
        L_0x0043:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            java.lang.String r2 = r8.mDevIp
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.streamax.utils.LogUtils.e((java.lang.String) r1, (java.lang.String) r0)
            java.util.Map r7 = connDeviceByC2C(r7, r8, r9)
            if (r7 == 0) goto L_0x0066
            java.lang.Object r8 = r7.get(r3)
            java.lang.Integer r8 = (java.lang.Integer) r8
            r8.intValue()
        L_0x0066:
            return r7
        L_0x0067:
            java.lang.String r0 = r8.mDevIp
            if (r0 == 0) goto L_0x00a8
            int r0 = r8.mMediaPort
            if (r0 <= 0) goto L_0x00a8
            java.lang.String r0 = r8.mDevIp
            boolean r0 = isIPAddr(r0)
            if (r0 != 0) goto L_0x007f
            java.lang.String r0 = r8.mDevIp
            boolean r0 = isDomain(r0)
            if (r0 == 0) goto L_0x00a8
        L_0x007f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            java.lang.String r2 = r8.mDevIp
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.streamax.utils.LogUtils.e((java.lang.String) r1, (java.lang.String) r0)
            java.util.Map r0 = connDeviceByC2C(r7, r8, r9)
            if (r0 == 0) goto L_0x00a4
            java.lang.Object r2 = r0.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            goto L_0x00a5
        L_0x00a4:
            r2 = -1
        L_0x00a5:
            if (r2 != 0) goto L_0x00aa
            return r0
        L_0x00a8:
            r0 = 0
            r2 = -1
        L_0x00aa:
            int r5 = com.streamax.client.MyApp.loginType
            if (r5 != 0) goto L_0x00b1
            java.lang.String r5 = r8.mDevIp
            goto L_0x00b3
        L_0x00b1:
            java.lang.String r5 = r8.mRemark
        L_0x00b3:
            if (r2 != r4) goto L_0x00cd
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "connDeviceByAuto nat, devInfo: "
            r0.append(r6)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.streamax.utils.LogUtils.e((java.lang.String) r1, (java.lang.String) r0)
            java.util.Map r0 = connDeviceByNAT(r7, r8, r9)
        L_0x00cd:
            if (r0 == 0) goto L_0x00d9
            java.lang.Object r2 = r0.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
        L_0x00d9:
            if (r2 != r4) goto L_0x00f3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "connDeviceByAuto msg, devInfo: "
            r0.append(r2)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.streamax.utils.LogUtils.e((java.lang.String) r1, (java.lang.String) r0)
            java.util.Map r0 = connDeviceByMSG(r7, r8, r9)
        L_0x00f3:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "connDeviceByAuto end, devInfo: "
            r7.append(r8)
            r7.append(r5)
            java.lang.String r7 = r7.toString()
            com.streamax.utils.LogUtils.e((java.lang.String) r1, (java.lang.String) r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.proxy.ConnDeviceProxy.connDeviceByAuto(com.dvr.net.DvrNet, com.streamax.client.DevInfoBean, com.streamax.client.MyApp):java.util.Map");
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
