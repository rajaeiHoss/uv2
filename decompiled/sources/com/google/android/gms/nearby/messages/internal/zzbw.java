package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.internal.zzcvf;
import com.google.android.gms.nearby.messages.SubscribeCallback;

final class zzbw extends zzab {
    private static final zzcvf<SubscribeCallback> zzkdu = new zzbx();
    private final zzci<SubscribeCallback> zzhnp;

    public zzbw(zzci<SubscribeCallback> zzci) {
        this.zzhnp = zzci;
    }

    public final void onExpired() {
        this.zzhnp.zza(zzkdu);
    }
}
