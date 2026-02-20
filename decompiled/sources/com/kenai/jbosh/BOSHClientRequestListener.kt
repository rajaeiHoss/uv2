package com.kenai.jbosh

interface BOSHClientRequestListener {
    fun requestSent(bOSHMessageEvent: BOSHMessageEvent)
}
