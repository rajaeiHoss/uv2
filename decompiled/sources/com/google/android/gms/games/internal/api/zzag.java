package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;

final class zzag implements Invitations.LoadInvitationsResult {
    private /* synthetic */ Status zzetp;

    zzag(zzaf zzaf, Status status) {
        this.zzetp = status;
    }

    public final InvitationBuffer getInvitations() {
        return new InvitationBuffer(DataHolder.zzbz(14));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
