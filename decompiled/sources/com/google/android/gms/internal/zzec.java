package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzec extends zzeu {
    private static final Object zzakj = new Object();
    private static volatile Long zzeh;

    public zzec(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 44);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        if (zzeh == null) {
            synchronized (zzakj) {
                if (zzeh == null) {
                    zzeh = (Long) this.zzaku.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzakm) {
            this.zzakm.zzeh = zzeh;
        }
    }
}
