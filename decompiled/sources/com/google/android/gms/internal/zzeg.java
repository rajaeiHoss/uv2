package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzeg extends zzeu {
    private static final Object zzakj = new Object();
    private static volatile Long zzakn;

    public zzeg(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 22);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        if (zzakn == null) {
            synchronized (zzakj) {
                if (zzakn == null) {
                    zzakn = (Long) this.zzaku.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzakm) {
            this.zzakm.zzdt = zzakn;
        }
    }
}
