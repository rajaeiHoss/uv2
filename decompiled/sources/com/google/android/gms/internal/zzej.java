package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzej extends zzeu {
    public zzej(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 3);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzakm) {
            zzcz zzcz = new zzcz((String) this.zzaku.invoke((Object) null, new Object[]{this.zzagq.getContext()}));
            synchronized (this.zzakm) {
                this.zzakm.zzdb = Long.valueOf(zzcz.zzaif);
                this.zzakm.zzew = Long.valueOf(zzcz.zzaig);
            }
        }
    }
}
