package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityClient;

final class zzag extends zzdo<zzhg, CapabilityClient.OnCapabilityChangedListener> {
    private final CapabilityClient.OnCapabilityChangedListener zzlsw;

    zzag(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, zzck<CapabilityClient.OnCapabilityChangedListener> zzck) {
        super(zzck);
        this.zzlsw = onCapabilityChangedListener;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzhg zzhg, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzhg.zza((zzn<Status>) new zzgg(taskCompletionSource), (CapabilityApi.CapabilityListener) this.zzlsw);
    }
}
