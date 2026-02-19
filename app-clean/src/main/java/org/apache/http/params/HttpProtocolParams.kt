package org.apache.http.params

import org.apache.http.HttpVersion

class HttpProtocolParams private constructor() {
    companion object {
        @JvmStatic
        fun setVersion(params: BasicHttpParams?, version: HttpVersion?) {
            params?.setParameter("http.protocol.version", version)
        }

        @JvmStatic
        fun setUseExpectContinue(params: BasicHttpParams?, enabled: Boolean) {
            params?.setParameter("http.protocol.expect-continue", enabled)
        }
    }
}
