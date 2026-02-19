package com.streamax.config.db;

public class DeviceInfoBean {
    public int mCurrentChannel;
    public int mCurrentDay;
    public int mCurrentPlan;
    public int mCurrentTotalChannel;
    public int mCurrentTotalPlan;
    public String mDeviceIp;

    public String toString() {
        return "mDeviceIp:" + this.mDeviceIp + ",mCurrentChannel:" + this.mCurrentChannel + ",mCurrentDay:" + this.mCurrentDay + ",mCurrentPlan:" + this.mCurrentPlan + ",mCurrentTotalChannel:" + this.mCurrentTotalChannel + ",mCurrentTotalPlan:" + this.mCurrentTotalPlan;
    }
}
