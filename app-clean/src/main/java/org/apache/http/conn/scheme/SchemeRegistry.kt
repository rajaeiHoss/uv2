package org.apache.http.conn.scheme

open class SchemeRegistry {
    private val schemes: MutableMap<String?, Scheme> = HashMap()

    open fun register(scheme: Scheme?): Scheme? {
        if (scheme != null) {
            schemes[scheme.getName()] = scheme
        }
        return scheme
    }
}
