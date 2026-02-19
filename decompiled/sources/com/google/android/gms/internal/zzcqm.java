package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcqm extends zzcq<zzcov, EndpointDiscoveryCallback> {
    private /* synthetic */ zzci zzhsp;
    private /* synthetic */ String zzjym;
    private /* synthetic */ zzcpz zzjyo;
    private /* synthetic */ DiscoveryOptions zzjyp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcqm(zzcpz zzcpz, zzci zzci, String str, zzci zzci2, DiscoveryOptions discoveryOptions) {
        super(zzci);
        this.zzjyo = zzcpz;
        this.zzjym = str;
        this.zzhsp = zzci2;
        this.zzjyp = discoveryOptions;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzcov zzcov, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zzcov.zza((zzn<Status>) new zzcqu(this.zzjyo, taskCompletionSource), this.zzjym, (zzci<EndpointDiscoveryCallback>) this.zzhsp, this.zzjyp);
    }
}
