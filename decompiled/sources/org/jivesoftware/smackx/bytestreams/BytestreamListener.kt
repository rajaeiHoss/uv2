package org.jivesoftware.smackx.bytestreams

interface BytestreamListener {
    fun incomingBytestreamRequest(bytestreamRequest: BytestreamRequest)
}
