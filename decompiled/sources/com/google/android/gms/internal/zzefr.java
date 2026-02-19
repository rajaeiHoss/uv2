package com.google.android.gms.internal;

final class zzefr implements Runnable {
    private /* synthetic */ zzefp zznck;

    zzefr(zzefp zzefp) {
        this.zznck = zzefp;
    }

    public final void run() {
        if (this.zznck.zzncb != null) {
            this.zznck.zzncb.zzpy("0");
            this.zznck.zzbxm();
        }
    }
}
