package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbq;

final class zzcpw extends zzcsu {
    private final zzn<Status> zzhmf;

    zzcpw(zzn<Status> zzn) {
        this.zzhmf = (zzn) zzbq.checkNotNull(zzn);
    }

    public final void zzer(int i) {
        Status zzeq = zzcov.zzcm(i);
        if (zzeq.isSuccess()) {
            this.zzhmf.setResult(zzeq);
        } else {
            this.zzhmf.zzu(zzeq);
        }
    }
}
