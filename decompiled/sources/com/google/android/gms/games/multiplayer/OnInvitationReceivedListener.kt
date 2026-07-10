package com.google.android.gms.games.multiplayer

@java.lang.Deprecated
interface OnInvitationReceivedListener {
    fun onInvitationReceived(invitation: Invitation)

    fun onInvitationRemoved(invitationId: String)
}
