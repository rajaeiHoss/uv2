package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.DataApi;

final class zzgp extends zzgm<DataApi.DeleteDataItemsResult> {
    public zzgp(zzn<DataApi.DeleteDataItemsResult> zzn) {
        super(zzn);
    }

    public final void zza(zzdg zzdg) {
        zzav(new zzch(zzgd.zzdg(zzdg.statusCode), zzdg.zzlug));
    }
}
