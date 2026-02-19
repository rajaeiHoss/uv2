package org.apache.http.message

import org.apache.http.ProtocolVersion
import org.apache.http.StatusLine

open class BasicStatusLine(
    private val protocolVersion: ProtocolVersion?,
    private val statusCode: Int,
    private val reasonPhrase: String?
) : StatusLine {
    open fun getProtocolVersion(): ProtocolVersion? = protocolVersion

    override fun getStatusCode(): Int = statusCode

    open fun getReasonPhrase(): String? = reasonPhrase
}
