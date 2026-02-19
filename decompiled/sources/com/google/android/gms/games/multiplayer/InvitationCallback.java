package com.google.android.gms.games.multiplayer;

public abstract class InvitationCallback implements OnInvitationReceivedListener {
    public abstract void onInvitationReceived(Invitation invitation);

    public abstract void onInvitationRemoved(String str);
}
