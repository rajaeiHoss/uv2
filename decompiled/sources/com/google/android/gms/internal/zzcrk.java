package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.connection.AdvertisingOptions;

final class zzcrk extends zzcrs {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zzjym;
    private /* synthetic */ AdvertisingOptions zzjyn;
    private /* synthetic */ zzci zzjze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrk(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, String str2, zzci zzci, AdvertisingOptions advertisingOptions) {
        super(googleApiClient, (zzcqx) null);
        this.val$name = str;
        this.zzjym = str2;
        this.zzjze = zzci;
        this.zzjyn = advertisingOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza(this, this.val$name, this.zzjym, this.zzjze, this.zzjyn);
    }
}
