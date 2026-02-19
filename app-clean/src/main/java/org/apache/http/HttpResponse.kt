package org.apache.http

interface HttpResponse {
    fun getStatusLine(): StatusLine?

    fun getAllHeaders(): Array<Header>

    fun getEntity(): HttpEntity?

    fun setHeaders(headers: Array<Header>?)

    fun setEntity(entity: HttpEntity?)
}
