package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzbf extends Handler {
    private /* synthetic */ zzba zzfyr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbf(zzba zzba, Looper looper) {
        super(looper);
        this.zzfyr = zzba;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.zzfyr.zzajr();
        } else if (i != 2) {
            int i2 = message.what;
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i2);
            Log.w("GoogleApiClientImpl", sb.toString());
        } else {
            this.zzfyr.resume();
        }
    }
}
