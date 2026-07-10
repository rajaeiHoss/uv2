package com.google.android.gms.games.multiplayer.turnbased

@java.lang.Deprecated
interface OnTurnBasedMatchUpdateReceivedListener {
    fun onTurnBasedMatchReceived(turnBasedMatch: TurnBasedMatch)

    fun onTurnBasedMatchRemoved(matchId: String)
}
