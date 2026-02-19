package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Message;

final class zzdbj implements Handler.Callback {
    private /* synthetic */ zzdbi zzkyh;

    zzdbj(zzdbi zzdbi) {
        this.zzkyh = zzdbi;
    }

    public final boolean handleMessage(Message message) {
        if (1 == message.what && zzdbe.zzkti.equals(message.obj)) {
            this.zzkyh.zzkyg.dispatch();
            if (!this.zzkyh.zzkyg.isPowerSaveMode()) {
                zzdbi zzdbi = this.zzkyh;
                zzdbi.zzs((long) zzdbi.zzkyg.zzktm);
            }
        }
        return true;
    }
}
