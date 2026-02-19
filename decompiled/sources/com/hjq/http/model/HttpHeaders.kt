package com.hjq.http.model

import com.hjq.http.EasyConfig
import java.util.HashMap

class HttpHeaders {
    private var mHeaders: HashMap<String, String> = EasyConfig.getInstance().headers

    fun put(str: String?, str2: String?) {
        if (str != null && str2 != null) {
            if (mHeaders === EasyConfig.getInstance().headers) {
                mHeaders = HashMap(mHeaders)
            }
            mHeaders[str] = str2
        }
    }

    fun remove(str: String?) {
        if (str != null) {
            if (mHeaders === EasyConfig.getInstance().headers) {
                mHeaders = HashMap(mHeaders)
            }
            mHeaders.remove(str)
        }
    }

    fun get(str: String): String? {
        return mHeaders[str]
    }

    fun isEmpty(): Boolean {
        return mHeaders.isEmpty()
    }

    fun getNames(): Set<String> {
        return mHeaders.keys
    }

    fun getHeaders(): HashMap<String, String> {
        return mHeaders
    }
}
