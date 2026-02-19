package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.internal.zzcvf;
import com.google.android.gms.nearby.messages.SubscribeCallback;

class zzbg extends zzab {
    private static final zzcvf<SubscribeCallback> zzkdu = new zzbh();
    private final zzci<SubscribeCallback> zzhnp;

    public zzbg(zzci<SubscribeCallback> zzci) {
        this.zzhnp = zzci;
    }

    public void onExpired() {
        zzci<SubscribeCallback> zzci = this.zzhnp;
        if (zzci != null) {
            zzci.zza(zzkdu);
        }
    }
}
