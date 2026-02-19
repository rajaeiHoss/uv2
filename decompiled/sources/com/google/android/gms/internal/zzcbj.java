package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.zzbb;
import com.google.android.gms.fitness.result.SessionStopResult;
import java.util.Collections;

final class zzcbj extends zzbye<SessionStopResult> {
    private /* synthetic */ String val$name = null;
    private /* synthetic */ String zzhni;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbj(zzcbh zzcbh, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.val$name = str;
        this.zzhni = str2;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbyb zzbyb) throws RemoteException {
        ((zzbzi) zzbyb.zzalw()).zza(new zzbb(this.val$name, this.zzhni, (zzbzq) new zzcbp(this, (zzcbi) null)));
    }

    /* access modifiers changed from: protected */
    public final SessionStopResult zzb(Status status) {
        return new SessionStopResult(status, Collections.emptyList());
    }
}
