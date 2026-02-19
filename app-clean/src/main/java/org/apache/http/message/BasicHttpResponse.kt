package org.apache.http.message

import org.apache.http.Header
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.StatusLine

open class BasicHttpResponse(private val statusLine: StatusLine?) : HttpResponse {
    private var headers: Array<Header> = emptyArray()
    private var entity: HttpEntity? = null

    override fun getStatusLine(): StatusLine? = statusLine

    override fun getAllHeaders(): Array<Header> = headers

    override fun getEntity(): HttpEntity? = entity

    override fun setHeaders(headers: Array<Header>?) {
        this.headers = headers ?: emptyArray()
    }

    override fun setEntity(entity: HttpEntity?) {
        this.entity = entity
    }
}
