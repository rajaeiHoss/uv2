package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

public final class InvitationBuffer extends zzg<Invitation> {
    public InvitationBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public final String zzalj() {
        return "external_invitation_id";
    }

    /* access modifiers changed from: protected */
    public final Invitation zzl(int i, int i2) {
        return new zzb(this.zzfxb, i, i2);
    }
}
