package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;

final class zzav extends zzbe {
    private /* synthetic */ zzci zzkdo;
    private /* synthetic */ zzak zzkdp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzav(zzak zzak, zzci zzci, zzci zzci2) {
        super(zzci);
        this.zzkdp = zzak;
        this.zzkdo = zzci2;
    }

    public final void onExpired() {
        this.zzkdp.zza((zzck<?>) this.zzkdo.zzakx());
        super.onExpired();
    }
}
