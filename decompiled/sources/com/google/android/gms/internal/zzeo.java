package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzeo extends zzeu {
    private final zzdu zzahu;

    public zzeo(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2, zzdu zzdu) {
        super(zzdm, str, str2, zzba, i, 53);
        this.zzahu = zzdu;
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        if (this.zzahu != null) {
            this.zzakm.zzen = (Long) this.zzaku.invoke((Object) null, new Object[]{Long.valueOf(this.zzahu.zzau())});
        }
    }
}
