package org.apache.http

interface Header {
    fun getName(): String?

    fun getValue(): String?
}
