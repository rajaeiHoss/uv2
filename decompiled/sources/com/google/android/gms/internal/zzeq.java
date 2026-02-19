package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzeq extends zzeu {
    public zzeq(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 48);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        zzba zzba;
        int i;
        this.zzakm.zzej = 2;
        boolean booleanValue = ((Boolean) this.zzaku.invoke((Object) null, new Object[]{this.zzagq.getContext()})).booleanValue();
        synchronized (this.zzakm) {
            if (booleanValue) {
                zzba = this.zzakm;
                i = 1;
            } else {
                zzba = this.zzakm;
                i = 0;
            }
            zzba.zzej = i;
        }
    }
}
