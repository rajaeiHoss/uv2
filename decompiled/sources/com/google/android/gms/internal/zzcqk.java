package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcqk extends zzcq<zzcov, Object> {
    private /* synthetic */ String val$name;
    private /* synthetic */ zzci zzhsp;
    private /* synthetic */ String zzjym;
    private /* synthetic */ AdvertisingOptions zzjyn;
    private /* synthetic */ zzcpz zzjyo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcqk(zzcpz zzcpz, zzci zzci, String str, String str2, zzci zzci2, AdvertisingOptions advertisingOptions) {
        super(zzci);
        this.zzjyo = zzcpz;
        this.val$name = str;
        this.zzjym = str2;
        this.zzhsp = zzci2;
        this.zzjyn = advertisingOptions;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzcov zzcov, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zzcov.zza(new zzcqu(this.zzjyo, taskCompletionSource), this.val$name, this.zzjym, this.zzhsp, this.zzjyn);
    }
}
