package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;

final class zzau extends zzdo<zzhg, ChannelClient.ChannelCallback> {
    private final String zzlnm;
    private final ChannelApi.ChannelListener zzltd;

    zzau(ChannelApi.ChannelListener channelListener, String str, zzck<ChannelClient.ChannelCallback> zzck) {
        super(zzck);
        this.zzltd = channelListener;
        this.zzlnm = str;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzhg zzhg, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzhg.zza(new zzgg(taskCompletionSource), this.zzltd, this.zzlnm);
    }
}
