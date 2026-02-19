package org.apache.http.conn.params

import org.apache.http.params.BasicHttpParams

class ConnManagerParams private constructor() {
    companion object {
        @JvmStatic
        fun setMaxTotalConnections(params: BasicHttpParams?, max: Int) {
            params?.setParameter("http.conn-manager.max-total", max)
        }
    }
}
