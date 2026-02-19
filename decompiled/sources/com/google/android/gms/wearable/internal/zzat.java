package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;

final class zzat extends zzcq<zzhg, ChannelClient.ChannelCallback> {
    private String zzlnm;
    private IntentFilter[] zzlsb;
    private ChannelApi.ChannelListener zzltd;
    private zzci<ChannelApi.ChannelListener> zzlte;

    zzat(ChannelApi.ChannelListener channelListener, String str, IntentFilter[] intentFilterArr, zzci<ChannelClient.ChannelCallback> zzci, zzci<ChannelApi.ChannelListener> zzci2) {
        super(zzci);
        this.zzltd = channelListener;
        this.zzlsb = intentFilterArr;
        this.zzlnm = str;
        this.zzlte = zzci2;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzhg zzhg, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zzhg.zza((zzn<Status>) new zzgh(taskCompletionSource), this.zzltd, this.zzlte, this.zzlnm, this.zzlsb);
    }
}
