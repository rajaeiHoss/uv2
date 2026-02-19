package com.google.android.gms.iid;

abstract class zzad {
    private static zzad zziob;

    zzad() {
    }

    static synchronized zzad zzawy() {
        zzad zzad;
        synchronized (zzad.class) {
            if (zziob == null) {
                zziob = new zzx();
            }
            zzad = zziob;
        }
        return zzad;
    }

    /* access modifiers changed from: package-private */
    public abstract zzae<Boolean> zzf(String str, boolean z);
}
