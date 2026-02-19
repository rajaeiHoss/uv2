package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.DataApi;

final class zzgx extends zzgm<DataApi.GetFdForAssetResult> {
    public zzgx(zzn<DataApi.GetFdForAssetResult> zzn) {
        super(zzn);
    }

    public final void zza(zzee zzee) {
        zzav(new zzci(zzgd.zzdg(zzee.statusCode), zzee.zzjwp));
    }
}
