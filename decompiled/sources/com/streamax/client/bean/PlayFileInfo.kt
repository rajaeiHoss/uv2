package com.streamax.client.bean

import com.dvr.net.RemoteFileInfo

class PlayFileInfo : RemoteFileInfo() {
    @JvmField var mIsPlay: Boolean = false
    @JvmField var mSubTitle: String? = null
    @JvmField var mTitle: String? = null
}
