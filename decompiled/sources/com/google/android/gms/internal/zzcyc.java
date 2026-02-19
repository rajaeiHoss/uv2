package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.api.Status;

final class zzcyc extends zzcya {
    private /* synthetic */ zzcyb zzklg;

    zzcyc(zzcyb zzcyb) {
        this.zzklg = zzcyb;
    }

    public final void zzar(Status status) {
        if (this.zzklg.zzklf) {
            Log.d("SearchAuth", "ClearTokenImpl success");
        }
        this.zzklg.setResult(status);
    }
}
