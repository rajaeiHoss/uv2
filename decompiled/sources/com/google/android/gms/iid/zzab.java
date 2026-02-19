package com.google.android.gms.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzab extends Handler {
    private /* synthetic */ zzaa zzioa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzab(zzaa zzaa, Looper looper) {
        super(looper);
        this.zzioa = zzaa;
    }

    public final void handleMessage(Message message) {
        this.zzioa.zzd(message);
    }
}
