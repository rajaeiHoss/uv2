package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzed extends zzeu {
    private long startTime;

    public zzed(zzdm zzdm, String str, String str2, zzba zzba, long j, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 25);
        this.startTime = j;
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzaku.invoke((Object) null, new Object[0])).longValue();
        synchronized (this.zzakm) {
            this.zzakm.zzfg = Long.valueOf(longValue);
            if (this.startTime != 0) {
                this.zzakm.zzdp = Long.valueOf(longValue - this.startTime);
                this.zzakm.zzdu = Long.valueOf(this.startTime);
            }
        }
    }
}
