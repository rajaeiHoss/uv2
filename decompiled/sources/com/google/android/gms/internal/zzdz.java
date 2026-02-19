package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdz extends zzeu {
    private static volatile String zzaki;
    private static final Object zzakj = new Object();

    public zzdz(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 29);
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        this.zzakm.zzdv = "E";
        if (zzaki == null) {
            synchronized (zzakj) {
                if (zzaki == null) {
                    zzaki = (String) this.zzaku.invoke((Object) null, new Object[]{this.zzagq.getContext()});
                }
            }
        }
        synchronized (this.zzakm) {
            this.zzakm.zzdv = zzbv.zza(zzaki.getBytes(), true);
        }
    }
}
