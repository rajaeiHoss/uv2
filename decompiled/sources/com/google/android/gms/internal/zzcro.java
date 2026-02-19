package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzcro extends zzcru {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zzjyt;
    private /* synthetic */ zzci zzjze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcro(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, String str2, zzci zzci) {
        super(googleApiClient, (zzcqx) null);
        this.val$name = str;
        this.zzjyt = str2;
        this.zzjze = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza((zzn<Status>) this, this.val$name, this.zzjyt, (zzci<ConnectionLifecycleCallback>) this.zzjze);
    }
}
