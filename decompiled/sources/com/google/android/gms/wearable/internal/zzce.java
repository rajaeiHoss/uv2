package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.DataApi;

final class zzce implements zzc<DataApi.DataListener> {
    private /* synthetic */ IntentFilter[] zzlso;

    zzce(IntentFilter[] intentFilterArr) {
        this.zzlso = intentFilterArr;
    }

    public final void zza(zzhg zzhg, zzn<Status> zzn, DataApi.DataListener dataListener, zzci<DataApi.DataListener> zzci) throws RemoteException {
        zzhg.zza(zzn, dataListener, zzci, this.zzlso);
    }
}
