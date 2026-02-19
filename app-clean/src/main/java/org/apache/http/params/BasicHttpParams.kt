package org.apache.http.params

open class BasicHttpParams {
    private val params: MutableMap<String?, Any?> = HashMap()

    open fun setParameter(name: String?, value: Any?): BasicHttpParams {
        params[name] = value
        return this
    }

    open fun getParameter(name: String?): Any? = params[name]
}
