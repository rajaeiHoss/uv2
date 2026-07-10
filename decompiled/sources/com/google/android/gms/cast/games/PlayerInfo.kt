package com.google.android.gms.cast.games

import org.json.JSONObject

@java.lang.Deprecated
interface PlayerInfo {
    val playerData: JSONObject?

    val playerId: String

    val playerState: Int

    val isConnected: Boolean

    val isControllable: Boolean
}
