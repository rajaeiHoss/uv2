package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;

final class zzaa implements zzbo<Invitations.LoadInvitationsResult, InvitationBuffer> {
    zzaa() {
    }

    public final InvitationBuffer zzb(Invitations.LoadInvitationsResult result) {
        if (result == null) {
            return null;
        }
        return result.getInvitations();
    }
}
