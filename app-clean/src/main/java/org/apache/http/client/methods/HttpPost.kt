package org.apache.http.client.methods

import org.apache.http.HttpEntity

open class HttpPost(private val uri: String?) {
    private var entity: HttpEntity? = null

    open fun setEntity(entity: HttpEntity?) {
        this.entity = entity
    }

    open fun getEntity(): HttpEntity? = entity

    open fun setHeader(name: String?, value: String?) {
        // No-op stub.
    }

    open fun abort() {
        // No-op stub.
    }

    open fun getUri(): String? = uri
}
