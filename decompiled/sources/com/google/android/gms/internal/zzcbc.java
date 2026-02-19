package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

final class zzcbc extends zzbzl {
    private final zzn<ListSubscriptionsResult> zzhmf;

    private zzcbc(zzn<ListSubscriptionsResult> zzn) {
        this.zzhmf = zzn;
    }

    /* synthetic */ zzcbc(zzn zzn, zzcax zzcax) {
        this(zzn);
    }

    public final void zza(ListSubscriptionsResult listSubscriptionsResult) {
        this.zzhmf.setResult(listSubscriptionsResult);
    }
}
