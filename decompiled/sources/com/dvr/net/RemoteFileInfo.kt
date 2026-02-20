package com.dvr.net

import android.util.Log

open class RemoteFileInfo {
    @JvmField
    var FileTime: String? = null

    @JvmField
    var bLocked: Int = 0

    @JvmField
    var nChannel: Int = 0

    @JvmField
    var nDiskType: Int = 0

    @JvmField
    var nFileSize: Int = 0

    @JvmField
    var nType: Int = 0

    @JvmField
    var name: String? = null

    fun Print(str: String?) {
        Log.v(str, "nDiskType =" + nDiskType)
        Log.v(str, "FileTime =" + FileTime)
        Log.v(str, "name =" + name)
        Log.v(str, "nFileSize =" + nFileSize)
        Log.v(str, "nChannel =" + nChannel)
        Log.v(str, "nType =" + nType)
        Log.v(str, "bLocked =" + bLocked)
    }
}
