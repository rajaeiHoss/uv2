package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.DataApi;
import java.util.List;
import java.util.concurrent.FutureTask;

final class zzhb extends zzgm<DataApi.DataItemResult> {
    private final List<FutureTask<Boolean>> zzlvk;

    zzhb(zzn<DataApi.DataItemResult> zzn, List<FutureTask<Boolean>> list) {
        super(zzn);
        this.zzlvk = list;
    }

    public final void zza(zzfu zzfu) {
        zzav(new zzcg(zzgd.zzdg(zzfu.statusCode), zzfu.zzluq));
        if (zzfu.statusCode != 0) {
            for (FutureTask<Boolean> cancel : this.zzlvk) {
                cancel.cancel(true);
            }
        }
    }
}
