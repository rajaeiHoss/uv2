package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.Status;

final class zzavj extends zzavc {
    private /* synthetic */ zzavi zzehg;

    zzavj(zzavi zzavi) {
        this.zzehg = zzavi;
    }

    public final void zza(Status status, Intent intent) {
        Activity activity;
        this.zzehg.setResult(new zzavl(status, intent));
        if (AppInviteReferral.hasReferral(intent) && this.zzehg.zzehe && this.zzehg.zzehd != null && (activity = (Activity) this.zzehg.zzehd.get()) != null) {
            activity.startActivity(intent);
        }
    }
}
