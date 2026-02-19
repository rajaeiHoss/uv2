package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityClient;

final class zzaf extends zzcq<zzhg, CapabilityClient.OnCapabilityChangedListener> {
    private zzci<CapabilityClient.OnCapabilityChangedListener> zzgwf;
    private IntentFilter[] zzlsb;
    private CapabilityClient.OnCapabilityChangedListener zzlsw;

    zzaf(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, IntentFilter[] intentFilterArr, zzci<CapabilityClient.OnCapabilityChangedListener> zzci) {
        super(zzci);
        this.zzlsw = onCapabilityChangedListener;
        this.zzlsb = intentFilterArr;
        this.zzgwf = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzhg zzhg, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zzhg.zza((zzn<Status>) new zzgh(taskCompletionSource), (CapabilityApi.CapabilityListener) this.zzlsw, (zzci) this.zzgwf, this.zzlsb);
    }
}
