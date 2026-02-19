package org.apache.http.client

import java.io.IOException
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.conn.HttpConnectionManager
import org.apache.http.protocol.HttpContext

interface HttpClient {
    @Throws(IOException::class)
    fun execute(post: HttpPost?, context: HttpContext?): HttpResponse

    fun getConnectionManager(): HttpConnectionManager?
}
