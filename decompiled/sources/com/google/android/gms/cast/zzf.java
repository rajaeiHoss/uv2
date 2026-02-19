package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.internal.zzbdp;
import com.google.android.gms.internal.zzbea;

final class zzf extends zzbea {
    private /* synthetic */ String val$message;
    private /* synthetic */ String zzetf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zzetf = str;
        this.val$message = str2;
    }


    public final void zza(zzbdp zzbdp) throws RemoteException {
        try {
            zzbdp.zza(this.zzetf, this.val$message, (zzn<Status>) this);
        } catch (IllegalArgumentException | IllegalStateException unused) {
            zzbj(2001);
        }
    }
}
