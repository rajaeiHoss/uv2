package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;

final class zzaw extends zzbg {
    private /* synthetic */ zzci zzhsp;
    private /* synthetic */ zzak zzkdp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(zzak zzak, zzci zzci, zzci zzci2) {
        super(zzci);
        this.zzkdp = zzak;
        this.zzhsp = zzci2;
    }

    public final void onExpired() {
        this.zzkdp.zza((zzck<?>) this.zzhsp.zzakx());
        super.onExpired();
    }
}
