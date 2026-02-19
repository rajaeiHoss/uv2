package org.apache.http.message

import org.apache.http.Header

open class BasicHeader(private val name: String?, private val value: String?) : Header {
    override fun getName(): String? = name

    override fun getValue(): String? = value
}
