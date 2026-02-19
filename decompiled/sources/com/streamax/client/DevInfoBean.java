package com.streamax.client;

import com.streamax.utils.LogUtils;
import java.io.Serializable;

public class DevInfoBean implements Serializable {
    private static final long serialVersionUID = 1;
    public int mChCounts;
    public int mDevId;
    public String mDevIp;
    public String mDevName;
    public String mLinkMode = "";
    public int mMediaPort;
    public int mPush;
    public String mPwd;
    public String mRemark = "";
    public String mUsername;
    public int mWebPort;

    public void PrintOut() {
        LogUtils.log("devId:" + this.mDevId);
        LogUtils.log("devName:" + this.mDevName);
        LogUtils.log("devIp:" + this.mDevIp);
        LogUtils.log("MediaPort:" + this.mMediaPort);
        LogUtils.log("WebPort:" + this.mWebPort);
        LogUtils.log("chCounts:" + this.mChCounts);
        LogUtils.log("username:" + this.mUsername);
        LogUtils.log("pwd:" + this.mPwd);
        LogUtils.log("remark:" + this.mRemark);
    }

    public String toString() {
        return "DevInfoBean_devId:" + this.mDevId + ",devName:" + this.mDevName + ",devIp:" + this.mDevIp + ",MediaPort:" + this.mMediaPort + ",WebPort:" + this.mWebPort + ",username:" + this.mUsername + ",pwd:" + this.mPwd + ",remark:" + this.mRemark;
    }
}
