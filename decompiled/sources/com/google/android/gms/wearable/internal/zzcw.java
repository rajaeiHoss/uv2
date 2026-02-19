package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataClient;

final class zzcw extends zzdo<zzhg, DataClient.OnDataChangedListener> {
    private final DataClient.OnDataChangedListener zzlue;

    zzcw(DataClient.OnDataChangedListener onDataChangedListener, zzck<DataClient.OnDataChangedListener> zzck) {
        super(zzck);
        this.zzlue = onDataChangedListener;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzhg zzhg, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzhg.zza((zzn<Status>) new zzgg(taskCompletionSource), (DataApi.DataListener) this.zzlue);
    }
}
