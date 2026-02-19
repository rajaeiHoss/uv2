package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;

public final class zzas implements ChannelApi.ChannelListener {
    private final ChannelClient.ChannelCallback zzltc;

    public zzas(ChannelClient.ChannelCallback channelCallback) {
        this.zzltc = channelCallback;
    }

    private static ChannelClient.Channel zza(Channel channel) {
        return (ChannelClient.Channel) channel;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.zzltc.equals(((zzas) obj).zzltc);
    }

    public final int hashCode() {
        return this.zzltc.hashCode();
    }

    public final void onChannelClosed(Channel channel, int i, int i2) {
        this.zzltc.onChannelClosed(zza(channel), i, i2);
    }

    public final void onChannelOpened(Channel channel) {
        this.zzltc.onChannelOpened(zza(channel));
    }

    public final void onInputClosed(Channel channel, int i, int i2) {
        this.zzltc.onInputClosed(zza(channel), i, i2);
    }

    public final void onOutputClosed(Channel channel, int i, int i2) {
        this.zzltc.onOutputClosed(zza(channel), i, i2);
    }
}
