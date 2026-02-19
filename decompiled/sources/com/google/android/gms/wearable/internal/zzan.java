package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.wearable.ChannelApi;

final class zzan extends zzn<Status> {
    private final String zzeia;
    private ChannelApi.ChannelListener zzlta;

    zzan(GoogleApiClient googleApiClient, ChannelApi.ChannelListener channelListener, String str) {
        super(googleApiClient);
        this.zzlta = (ChannelApi.ChannelListener) zzbq.checkNotNull(channelListener);
        this.zzeia = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        zzhg.zza(this, this.zzlta, this.zzeia);
        this.zzlta = null;
    }

    public final /* synthetic */ Status zzb(Status status) {
        this.zzlta = null;
        return status;
    }
}
