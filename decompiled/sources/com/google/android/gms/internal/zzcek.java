package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.instantapps.LaunchData;
import com.google.android.gms.instantapps.zze;
import com.google.android.gms.instantapps.zzi;

final class zzcek extends zzcer<zze> {
    private /* synthetic */ String zzcrd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcek(zzcej zzcej, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzcrd = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzcef zzcef) throws RemoteException {
        zzcef.zza(new zzcel(this), this.zzcrd, new zzi());
    }

    /* access modifiers changed from: protected */
    public final zze zzb(Status status) {
        return new zzces(status, (LaunchData) null);
    }
}
