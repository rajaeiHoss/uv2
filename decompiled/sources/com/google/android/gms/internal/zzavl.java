package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.common.api.Status;

public final class zzavl implements AppInviteInvitationResult {
    private final Status mStatus;
    private final Intent zzehi;

    public zzavl(Status status, Intent intent) {
        this.mStatus = status;
        this.zzehi = intent;
    }

    public final Intent getInvitationIntent() {
        return this.zzehi;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
