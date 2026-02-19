package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.zzbq;

final class zzcj extends Handler {
    private /* synthetic */ zzci zzgav;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzcj(zzci zzci, Looper looper) {
        super(looper);
        this.zzgav = zzci;
    }

    public final void handleMessage(Message message) {
        boolean z = true;
        if (message.what != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        this.zzgav.zzb((zzcl) message.obj);
    }
}
