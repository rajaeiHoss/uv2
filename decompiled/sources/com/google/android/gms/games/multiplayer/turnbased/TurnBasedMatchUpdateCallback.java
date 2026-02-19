package com.google.android.gms.games.multiplayer.turnbased;

public abstract class TurnBasedMatchUpdateCallback implements OnTurnBasedMatchUpdateReceivedListener {
    public abstract void onTurnBasedMatchReceived(TurnBasedMatch turnBasedMatch);

    public abstract void onTurnBasedMatchRemoved(String str);
}
