package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;

final class zzgc implements ChannelApi.ChannelListener {
    private final String zzeia;
    private final ChannelApi.ChannelListener zzlvg;

    zzgc(String str, ChannelApi.ChannelListener channelListener) {
        this.zzeia = (String) zzbq.checkNotNull(str);
        this.zzlvg = (ChannelApi.ChannelListener) zzbq.checkNotNull(channelListener);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgc)) {
            return false;
        }
        zzgc zzgc = (zzgc) obj;
        return this.zzlvg.equals(zzgc.zzlvg) && this.zzeia.equals(zzgc.zzeia);
    }

    public final int hashCode() {
        return (this.zzeia.hashCode() * 31) + this.zzlvg.hashCode();
    }

    public final void onChannelClosed(Channel channel, int i, int i2) {
        this.zzlvg.onChannelClosed(channel, i, i2);
    }

    public final void onChannelOpened(Channel channel) {
        this.zzlvg.onChannelOpened(channel);
    }

    public final void onInputClosed(Channel channel, int i, int i2) {
        this.zzlvg.onInputClosed(channel, i, i2);
    }

    public final void onOutputClosed(Channel channel, int i, int i2) {
        this.zzlvg.onOutputClosed(channel, i, i2);
    }
}
