package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.ChannelApi;

final class zzal implements zzc<ChannelApi.ChannelListener> {
    private /* synthetic */ IntentFilter[] zzlso;

    zzal(IntentFilter[] intentFilterArr) {
        this.zzlso = intentFilterArr;
    }

    public final void zza(zzhg zzhg, zzn<Status> zzn, ChannelApi.ChannelListener channelListener, zzci<ChannelApi.ChannelListener> zzci) throws RemoteException {
        zzhg.zza(zzn, channelListener, zzci, (String) null, this.zzlso);
    }
}
