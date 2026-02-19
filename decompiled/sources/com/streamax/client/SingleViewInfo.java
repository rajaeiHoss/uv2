package com.streamax.client;

import com.dvr.net.DvrNet;

public class SingleViewInfo {
    public static final int CONN_FAILURE = 1;
    public static final int CONN_SUCCESS = 0;
    public int mChannel;
    public int mConnState = 0;
    public DevInfoBean mDevInfo;
    public String mDevIp;
    private DvrNet mDvrNet;

    public SingleViewInfo() {
        reset();
    }

    public void setConnState(int i) {
        this.mConnState = i;
    }

    public int getConnState() {
        return this.mConnState;
    }

    public void reset() {
        this.mDvrNet = null;
        this.mDevInfo = new DevInfoBean();
        this.mChannel = -1;
    }

    public boolean check(DevInfoBean devInfoBean, int i) {
        return i != -1 && devInfoBean.mDevIp.equals(this.mDevInfo.mDevIp) && devInfoBean.mMediaPort == this.mDevInfo.mMediaPort && i == this.mChannel;
    }

    public boolean check(DevInfoBean devInfoBean) {
        if (this.mChannel != -1 && devInfoBean.mDevIp.equals(this.mDevInfo.mDevIp) && devInfoBean.mMediaPort == this.mDevInfo.mMediaPort) {
            return true;
        }
        return false;
    }

    public DevInfoBean getDeviceInfoBean() {
        return this.mDevInfo;
    }

    public void setDeviceInfo(DevInfoBean devInfoBean) {
        this.mDevInfo = devInfoBean;
    }

    public DvrNet getNet() {
        return this.mDvrNet;
    }

    public void setNet(DvrNet dvrNet) {
        this.mDvrNet = dvrNet;
    }
}
