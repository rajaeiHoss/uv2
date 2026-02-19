package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.CapabilityApi;

final class zzt implements zzc<CapabilityApi.CapabilityListener> {
    private /* synthetic */ IntentFilter[] zzlso;

    zzt(IntentFilter[] intentFilterArr) {
        this.zzlso = intentFilterArr;
    }

    public final void zza(zzhg zzhg, zzn<Status> zzn, CapabilityApi.CapabilityListener capabilityListener, zzci<CapabilityApi.CapabilityListener> zzci) throws RemoteException {
        zzhg.zza(zzn, capabilityListener, zzci, this.zzlso);
    }
}
