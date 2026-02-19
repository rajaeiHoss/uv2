package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.fitness.result.SessionStopResult;

final class zzcbp extends zzbzr {
    private final zzn<SessionStopResult> zzhmf;

    private zzcbp(zzn<SessionStopResult> zzn) {
        this.zzhmf = zzn;
    }

    /* synthetic */ zzcbp(zzn zzn, zzcbi zzcbi) {
        this(zzn);
    }

    public final void zza(SessionStopResult sessionStopResult) {
        this.zzhmf.setResult(sessionStopResult);
    }
}
