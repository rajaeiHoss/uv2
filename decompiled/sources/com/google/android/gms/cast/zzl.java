package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.internal.zzbdp;
import com.google.android.gms.internal.zzbea;

final class zzl extends zzbea {
    private /* synthetic */ String val$sessionId;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzl(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.val$sessionId = str;
    }


    public final void zza(zzbdp zzbdp) throws RemoteException {
        if (TextUtils.isEmpty(this.val$sessionId)) {
            setResult(zzb(new Status(2001, "IllegalArgument: sessionId cannot be null or empty", (PendingIntent) null)));
            return;
        }
        try {
            zzbdp.zza(this.val$sessionId, (zzn<Status>) this);
        } catch (IllegalStateException unused) {
            zzbj(2001);
        }
    }
}
