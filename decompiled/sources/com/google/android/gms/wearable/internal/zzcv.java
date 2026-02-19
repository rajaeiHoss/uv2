package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataClient;

final class zzcv extends zzcq<zzhg, DataClient.OnDataChangedListener> {
    private zzci<DataClient.OnDataChangedListener> zzgwf;
    private IntentFilter[] zzlsb;
    private DataClient.OnDataChangedListener zzlue;

    zzcv(DataClient.OnDataChangedListener onDataChangedListener, IntentFilter[] intentFilterArr, zzci<DataClient.OnDataChangedListener> zzci) {
        super(zzci);
        this.zzlue = onDataChangedListener;
        this.zzlsb = intentFilterArr;
        this.zzgwf = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzhg zzhg, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zzhg.zza((zzn<Status>) new zzgh(taskCompletionSource), (DataApi.DataListener) this.zzlue, (zzci) this.zzgwf, this.zzlsb);
    }
}
