package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;

final /* synthetic */ class zzap implements zzbo {
    static final zzbo zzgui = new zzap();

    private zzap() {
    }

    public final Object zzb(Result result) {
        return (ChannelClient.Channel) ((ChannelApi.OpenChannelResult) result).getChannel();
    }
}
