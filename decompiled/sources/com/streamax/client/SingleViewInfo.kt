package com.streamax.client

import com.dvr.net.DvrNet

class SingleViewInfo {
    @JvmField var mChannel: Int = -1
    @JvmField var mConnState: Int = 0
    @JvmField var mDevInfo: DevInfoBean = DevInfoBean()
    @JvmField var mDevIp: String? = null
    private var mDvrNet: DvrNet? = null

    fun setConnState(state: Int) { mConnState = state }
    fun getConnState(): Int = mConnState
    fun reset() {
        mDvrNet = null
        mDevInfo = DevInfoBean()
        mChannel = -1
    }
    fun check(devInfoBean: DevInfoBean, channel: Int): Boolean =
        channel != -1 && devInfoBean.mDevIp == mDevInfo.mDevIp &&
            devInfoBean.mMediaPort == mDevInfo.mMediaPort && channel == mChannel
    fun check(devInfoBean: DevInfoBean): Boolean =
        mChannel != -1 && devInfoBean.mDevIp == mDevInfo.mDevIp &&
            devInfoBean.mMediaPort == mDevInfo.mMediaPort
    fun getDeviceInfoBean(): DevInfoBean = mDevInfo
    fun setDeviceInfo(info: DevInfoBean) { mDevInfo = info }
    fun getNet(): DvrNet? = mDvrNet
    fun setNet(net: DvrNet?) { mDvrNet = net }

    companion object {
        const val CONN_FAILURE: Int = 1
        const val CONN_SUCCESS: Int = 0
    }
}
