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

    fun Print(tag: String?) {
        Log.v(tag, "nDiskType =" + nDiskType)
        Log.v(tag, "FileTime =" + FileTime)
        Log.v(tag, "name =" + name)
        Log.v(tag, "nFileSize =" + nFileSize)
        Log.v(tag, "nChannel =" + nChannel)
        Log.v(tag, "nType =" + nType)
        Log.v(tag, "bLocked =" + bLocked)
    }
}
