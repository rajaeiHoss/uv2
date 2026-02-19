package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

final class zzft implements Handler.Callback {
    private /* synthetic */ zzfs zzktw;

    zzft(zzfs zzfs) {
        this.zzktw = zzfs;
    }

    public final boolean handleMessage(Message message) {
        if (1 == message.what && zzfo.zzkti.equals(message.obj)) {
            this.zzktw.zzktv.dispatch();
            if (!this.zzktw.zzktv.isPowerSaveMode()) {
                zzfs zzfs = this.zzktw;
                zzfs.zzs((long) zzfs.zzktv.zzktm);
            }
        }
        return true;
    }
}
