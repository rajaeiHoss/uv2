package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import java.util.List;

public final class zzhk<T> extends zzen {
    private final IntentFilter[] zzlux;
    private zzci<Object> zzlvx;
    private zzci<Object> zzlvy;
    private zzci<DataApi.DataListener> zzlvz;
    private zzci<MessageApi.MessageListener> zzlwa;
    private zzci<Object> zzlwb;
    private zzci<Object> zzlwc;
    private zzci<ChannelApi.ChannelListener> zzlwd;
    private zzci<CapabilityApi.CapabilityListener> zzlwe;
    private final String zzlwf;

    private zzhk(IntentFilter[] intentFilterArr, String str) {
        this.zzlux = (IntentFilter[]) zzbq.checkNotNull(intentFilterArr);
        this.zzlwf = str;
    }

    public static zzhk<ChannelApi.ChannelListener> zza(zzci<ChannelApi.ChannelListener> zzci, String str, IntentFilter[] intentFilterArr) {
        zzhk<ChannelApi.ChannelListener> zzhk = new zzhk<>(intentFilterArr, (String) zzbq.checkNotNull(str));
        zzhk.zzlwd = (zzci) zzbq.checkNotNull(zzci);
        return zzhk;
    }

    public static zzhk<DataApi.DataListener> zza(zzci<DataApi.DataListener> zzci, IntentFilter[] intentFilterArr) {
        zzhk<DataApi.DataListener> zzhk = new zzhk<>(intentFilterArr, (String) null);
        zzhk.zzlvz = (zzci) zzbq.checkNotNull(zzci);
        return zzhk;
    }

    public static zzhk<MessageApi.MessageListener> zzb(zzci<MessageApi.MessageListener> zzci, IntentFilter[] intentFilterArr) {
        zzhk<MessageApi.MessageListener> zzhk = new zzhk<>(intentFilterArr, (String) null);
        zzhk.zzlwa = (zzci) zzbq.checkNotNull(zzci);
        return zzhk;
    }

    public static zzhk<ChannelApi.ChannelListener> zzc(zzci<ChannelApi.ChannelListener> zzci, IntentFilter[] intentFilterArr) {
        zzhk<ChannelApi.ChannelListener> zzhk = new zzhk<>(intentFilterArr, (String) null);
        zzhk.zzlwd = (zzci) zzbq.checkNotNull(zzci);
        return zzhk;
    }

    public static zzhk<CapabilityApi.CapabilityListener> zzd(zzci<CapabilityApi.CapabilityListener> zzci, IntentFilter[] intentFilterArr) {
        zzhk<CapabilityApi.CapabilityListener> zzhk = new zzhk<>(intentFilterArr, (String) null);
        zzhk.zzlwe = (zzci) zzbq.checkNotNull(zzci);
        return zzhk;
    }

    private static void zzo(zzci<?> zzci) {
        if (zzci != null) {
            zzci.clear();
        }
    }

    public final void clear() {
        zzo((zzci<?>) null);
        this.zzlvx = null;
        zzo((zzci<?>) null);
        this.zzlvy = null;
        zzo(this.zzlvz);
        this.zzlvz = null;
        zzo(this.zzlwa);
        this.zzlwa = null;
        zzo((zzci<?>) null);
        this.zzlwb = null;
        zzo((zzci<?>) null);
        this.zzlwc = null;
        zzo(this.zzlwd);
        this.zzlwd = null;
        zzo(this.zzlwe);
        this.zzlwe = null;
    }

    public final void onConnectedNodes(List<zzfo> list) {
    }

    public final void zza(zzah zzah) {
        zzci<CapabilityApi.CapabilityListener> zzci = this.zzlwe;
        if (zzci != null) {
            zzci.zza(new zzho(zzah));
        }
    }

    public final void zza(zzaw zzaw) {
        zzci<ChannelApi.ChannelListener> zzci = this.zzlwd;
        if (zzci != null) {
            zzci.zza(new zzhn(zzaw));
        }
    }

    public final void zza(zzfe zzfe) {
        zzci<MessageApi.MessageListener> zzci = this.zzlwa;
        if (zzci != null) {
            zzci.zza(new zzhm(zzfe));
        }
    }

    public final void zza(zzfo zzfo) {
    }

    public final void zza(zzi zzi) {
    }

    public final void zza(zzl zzl) {
    }

    public final void zzb(zzfo zzfo) {
    }

    public final IntentFilter[] zzblz() {
        return this.zzlux;
    }

    public final String zzbma() {
        return this.zzlwf;
    }

    public final void zzbo(DataHolder dataHolder) {
        zzci<DataApi.DataListener> zzci = this.zzlvz;
        if (zzci != null) {
            zzci.zza(new zzhl(dataHolder));
        } else {
            dataHolder.close();
        }
    }
}
