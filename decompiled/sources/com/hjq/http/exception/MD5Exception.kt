package com.hjq.http.exception

class MD5Exception(str: String, str2: String) : HttpException(str) {
    private val mMD5: String = str2

    fun getMD5(): String {
        return mMD5
    }
}
