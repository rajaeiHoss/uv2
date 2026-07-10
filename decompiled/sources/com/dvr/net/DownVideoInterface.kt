package com.dvr.net

interface DownVideoInterface {
    fun DownVideoCallback(nativeHandle: Long, status: Int, totalBytes: Int, currentBytes: Int)
}
