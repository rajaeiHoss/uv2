package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.internal.zzbdp;

final class zzi extends Cast.zza {
    private /* synthetic */ String val$sessionId;
    private /* synthetic */ String zzetg;
    private /* synthetic */ zzab zzeti = null;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzi(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str, String str2, zzab zzab) {
        super(googleApiClient);
        this.zzetg = str;
        this.val$sessionId = str2;
    }


    public final void zza(zzbdp zzbdp) throws RemoteException {
        try {
            zzbdp.zza(this.zzetg, this.val$sessionId, this.zzeti, (zzn<Cast.ApplicationConnectionResult>) this);
        } catch (IllegalStateException unused) {
            zzbj(2001);
        }
    }
}
