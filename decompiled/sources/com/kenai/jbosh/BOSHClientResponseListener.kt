package com.kenai.jbosh

interface BOSHClientResponseListener {
    fun responseReceived(bOSHMessageEvent: BOSHMessageEvent)
}
