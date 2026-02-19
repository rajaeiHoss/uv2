package org.apache.http.impl.client

import java.io.ByteArrayInputStream
import java.io.IOException
import org.apache.http.HttpResponse
import org.apache.http.ProtocolVersion
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.conn.HttpConnectionManager
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
import org.apache.http.message.BasicHttpResponse
import org.apache.http.message.BasicStatusLine
import org.apache.http.entity.BasicHttpEntity
import org.apache.http.params.BasicHttpParams
import org.apache.http.protocol.HttpContext

open class DefaultHttpClient(
    manager: ThreadSafeClientConnManager?,
    params: BasicHttpParams?
) : HttpClient {
    private val connectionManager: HttpConnectionManager? = manager

    @Throws(IOException::class)
    override fun execute(post: HttpPost?, context: HttpContext?): HttpResponse {
        val response = BasicHttpResponse(
            BasicStatusLine(ProtocolVersion("HTTP", 1, 1), 501, "Not Implemented")
        )
        val entity = BasicHttpEntity()
        entity.setContent(ByteArrayInputStream(ByteArray(0)))
        entity.setContentLength(0)
        response.setEntity(entity)
        return response
    }

    override fun getConnectionManager(): HttpConnectionManager? = connectionManager
}
