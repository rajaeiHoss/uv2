package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzel extends zzeu {
    public zzel(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 61);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzaku.invoke((Object) null, new Object[]{this.zzagq.getContext(), Boolean.valueOf(this.zzagq.zzal())})).longValue();
        synchronized (this.zzakm) {
            this.zzakm.zzex = Long.valueOf(longValue);
        }
    }
}
