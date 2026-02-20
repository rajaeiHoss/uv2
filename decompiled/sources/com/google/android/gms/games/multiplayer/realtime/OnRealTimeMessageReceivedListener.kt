package com.google.android.gms.games.multiplayer.realtime

interface OnRealTimeMessageReceivedListener : RealTimeMessageReceivedListener {
    override fun onRealTimeMessageReceived(realTimeMessage: RealTimeMessage)
}
