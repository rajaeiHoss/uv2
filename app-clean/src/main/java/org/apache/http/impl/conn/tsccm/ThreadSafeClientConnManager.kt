package org.apache.http.impl.conn.tsccm

import org.apache.http.conn.HttpConnectionManager
import org.apache.http.conn.scheme.SchemeRegistry
import org.apache.http.params.BasicHttpParams

open class ThreadSafeClientConnManager(
    params: BasicHttpParams?,
    registry: SchemeRegistry?
) : HttpConnectionManager {
    override fun shutdown() {
        // No-op stub.
    }
}
