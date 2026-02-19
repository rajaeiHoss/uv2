package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.lang.ref.WeakReference;

final class zzavi extends zzavd<AppInviteInvitationResult> {
    /* access modifiers changed from: private */
    public final WeakReference<Activity> zzehd;
    /* access modifiers changed from: private */
    public final boolean zzehe;
    private final Intent zzehf;

    public zzavi(zzavb zzavb, GoogleApiClient googleApiClient, Activity activity, boolean z) {
        super(googleApiClient);
        this.zzehe = z;
        this.zzehd = new WeakReference<>(activity);
        this.zzehf = activity != null ? activity.getIntent() : null;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzavk zzavk) throws RemoteException {
        zzavj zzavj;
        if (AppInviteReferral.hasReferral(this.zzehf)) {
            setResult(new zzavl(Status.zzftq, this.zzehf));
            zzavj = null;
        } else {
            zzavj = new zzavj(this);
        }
        zzavk.zza(zzavj);
    }

    /* access modifiers changed from: protected */
    public final AppInviteInvitationResult zzb(Status status) {
        return new zzavl(status, new Intent());
    }
}
