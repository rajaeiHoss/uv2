package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzeb extends zzeu {
    public zzeb(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 5);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        this.zzakm.zzdd = -1L;
        this.zzakm.zzde = -1L;
        int[] iArr = (int[]) this.zzaku.invoke((Object) null, new Object[]{this.zzagq.getContext()});
        synchronized (this.zzakm) {
            this.zzakm.zzdd = Long.valueOf((long) iArr[0]);
            this.zzakm.zzde = Long.valueOf((long) iArr[1]);
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrj)).booleanValue() && iArr[2] != Integer.MIN_VALUE) {
                this.zzakm.zzev = Long.valueOf((long) iArr[2]);
            }
        }
    }
}
