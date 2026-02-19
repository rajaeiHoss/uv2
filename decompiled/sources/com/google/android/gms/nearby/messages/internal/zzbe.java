package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.internal.zzcvf;
import com.google.android.gms.nearby.messages.PublishCallback;

class zzbe extends zzv {
    private static final zzcvf<PublishCallback> zzkdu = new zzbf();
    private final zzci<PublishCallback> zzhnp;

    public zzbe(zzci<PublishCallback> zzci) {
        this.zzhnp = zzci;
    }

    public void onExpired() {
        zzci<PublishCallback> zzci = this.zzhnp;
        if (zzci != null) {
            zzci.zza(zzkdu);
        }
    }
}
