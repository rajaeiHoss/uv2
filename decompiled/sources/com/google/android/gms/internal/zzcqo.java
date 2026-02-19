package com.google.android.gms.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;

final class zzcqo implements OnFailureListener {
    private /* synthetic */ String zzjxs;
    private /* synthetic */ zzcpz zzjyo;

    zzcqo(zzcpz zzcpz, String str) {
        this.zzjyo = zzcpz;
        this.zzjxs = str;
    }

    public final void onFailure(Exception exc) {
        if (!(exc instanceof ApiException) || ((ApiException) exc).getStatusCode() != 8003) {
            this.zzjyo.zzkw(this.zzjxs);
        }
    }
}
