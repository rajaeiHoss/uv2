package com.google.android.gms.games.request

@java.lang.Deprecated
interface OnRequestReceivedListener {
    fun onRequestReceived(request: GameRequest)

    fun onRequestRemoved(requestId: String)
}
