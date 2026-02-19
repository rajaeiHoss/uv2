package org.apache.http

open class ProtocolVersion(
    private val protocol: String?,
    private val major: Int,
    private val minor: Int
) {
    open fun getProtocol(): String? = protocol

    open fun getMajor(): Int = major

    open fun getMinor(): Int = minor
}
