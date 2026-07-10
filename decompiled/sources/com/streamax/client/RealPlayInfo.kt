package com.streamax.client

import com.dvr.avstream.AVStream
import com.dvr.net.DvrNet

class RealPlayInfo {
    @JvmField var av: AVStream? = null
    @JvmField var nId: Int = 0
    @JvmField var nView: Int = 0
    @JvmField var f4net: DvrNet? = null
}
