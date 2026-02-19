package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.ChannelClient;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzao extends ChannelClient {
    private final zzaj zzltb = new zzaj();

    public zzao(Activity activity, GoogleApi.zza zza) {
        super(activity, zza);
    }

    public zzao(Context context, GoogleApi.zza zza) {
        super(context, zza);
    }

    private static zzay zza(ChannelClient.Channel channel) {
        zzbq.checkNotNull(channel, "channel must not be null");
        return (zzay) channel;
    }

    public final Task<Void> close(ChannelClient.Channel channel) {
        return zzbj.zzb(zza(channel).close(zzahw()));
    }

    public final Task<Void> close(ChannelClient.Channel channel, int i) {
        return zzbj.zzb(zza(channel).close(zzahw(), i));
    }

    public final Task<InputStream> getInputStream(ChannelClient.Channel channel) {
        return zzbj.zza(zza(channel).getInputStream(zzahw()), zzaq.zzgui);
    }

    public final Task<OutputStream> getOutputStream(ChannelClient.Channel channel) {
        return zzbj.zza(zza(channel).getOutputStream(zzahw()), zzar.zzgui);
    }

    public final Task<ChannelClient.Channel> openChannel(String str, String str2) {
        return zzbj.zza(this.zzltb.openChannel(zzahw(), str, str2), zzap.zzgui);
    }

    public final Task<Void> receiveFile(ChannelClient.Channel channel, Uri uri, boolean z) {
        return zzbj.zzb(zza(channel).receiveFile(zzahw(), uri, z));
    }

    public final Task<Void> registerChannelCallback(ChannelClient.Channel channel, ChannelClient.ChannelCallback channelCallback) {
        String token = ((zzay) channel).getToken();
        zzbq.checkNotNull(channelCallback, "listener is null");
        Looper looper = getLooper();
        String valueOf = String.valueOf(token);
        zzci zzb = zzcm.zzb(channelCallback, looper, valueOf.length() != 0 ? "ChannelListener:".concat(valueOf) : new String("ChannelListener:"));
        IntentFilter[] intentFilterArr = {zzgj.zzoe("com.google.android.gms.wearable.CHANNEL_EVENT")};
        zzas zzas = new zzas(channelCallback);
        return zza(new zzat(zzas, token, intentFilterArr, zzb, zzcm.zzb(zzas, getLooper(), "ChannelListener")), new zzau(zzas, token, zzb.zzakx()));
    }

    public final Task<Void> registerChannelCallback(ChannelClient.ChannelCallback channelCallback) {
        zzbq.checkNotNull(channelCallback, "listener is null");
        zzci zzb = zzcm.zzb(channelCallback, getLooper(), "ChannelListener");
        IntentFilter[] intentFilterArr = {zzgj.zzoe("com.google.android.gms.wearable.CHANNEL_EVENT")};
        zzas zzas = new zzas(channelCallback);
        return zza(new zzat(zzas, (String) null, intentFilterArr, zzb, zzcm.zzb(zzas, getLooper(), "ChannelListener")), new zzau(zzas, (String) null, zzb.zzakx()));
    }

    public final Task<Void> sendFile(ChannelClient.Channel channel, Uri uri) {
        return zzbj.zzb(zza(channel).sendFile(zzahw(), uri));
    }

    public final Task<Void> sendFile(ChannelClient.Channel channel, Uri uri, long j, long j2) {
        return zzbj.zzb(zza(channel).sendFile(zzahw(), uri, j, j2));
    }

    public final Task<Boolean> unregisterChannelCallback(ChannelClient.Channel channel, ChannelClient.ChannelCallback channelCallback) {
        String token = zza(channel).getToken();
        Looper looper = getLooper();
        String valueOf = String.valueOf(token);
        return zza((zzck<?>) zzcm.zzb(channelCallback, looper, valueOf.length() != 0 ? "ChannelListener:".concat(valueOf) : new String("ChannelListener:")).zzakx());
    }

    public final Task<Boolean> unregisterChannelCallback(ChannelClient.ChannelCallback channelCallback) {
        return zza((zzck<?>) zzcm.zzb(channelCallback, getLooper(), "ChannelListener").zzakx());
    }
}
