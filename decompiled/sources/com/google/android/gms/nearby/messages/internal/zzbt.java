package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.internal.zzcvf;
import com.google.android.gms.nearby.messages.PublishCallback;

final class zzbt extends zzv {
    private static final zzcvf<PublishCallback> zzkdu = new zzbu();
    private final zzci<PublishCallback> zzhnp;

    public zzbt(zzci<PublishCallback> zzci) {
        this.zzhnp = zzci;
    }

    public final void onExpired() {
        this.zzhnp.zza(zzkdu);
    }
}
