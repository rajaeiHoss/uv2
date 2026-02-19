package com.hjq.http.model

import com.hjq.http.EasyConfig
import java.util.HashMap

class HttpParams {
    private var mMultipart: Boolean = false
    private var mParams: HashMap<String, Any> = EasyConfig.getInstance().params

    fun put(str: String?, obj: Any?) {
        if (str != null && obj != null) {
            if (mParams === EasyConfig.getInstance().params) {
                mParams = HashMap(mParams)
            }
            mParams[str] = obj
        }
    }

    fun remove(str: String?) {
        if (str != null) {
            if (mParams === EasyConfig.getInstance().params) {
                mParams = HashMap(mParams)
            }
            mParams.remove(str)
        }
    }

    fun get(str: String): Any? {
        return mParams[str]
    }

    fun isEmpty(): Boolean {
        return mParams.isEmpty()
    }

    fun getNames(): Set<String> {
        return mParams.keys
    }

    fun getParams(): HashMap<String, Any> {
        return mParams
    }

    fun isMultipart(): Boolean {
        return mMultipart
    }

    fun setMultipart(z: Boolean) {
        mMultipart = z
    }
}
