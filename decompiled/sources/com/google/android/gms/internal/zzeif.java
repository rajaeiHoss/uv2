package com.google.android.gms.internal;

import java.util.Map;

final class zzeif implements Runnable {
    private /* synthetic */ zzegm zznhg;
    private /* synthetic */ zzeib zznhh;

    zzeif(zzeib zzeib, zzegm zzegm) {
        this.zznhh = zzeib;
        this.zznhg = zzegm;
    }

    public final void run() {
        synchronized (this.zznhh.zznhf) {
            if (this.zznhh.zznhf.containsKey(this.zznhg)) {
                for (zzegx resume : ((Map<?, zzegx>) this.zznhh.zznhf.get(this.zznhg)).values()) {
                    resume.resume();
                }
            }
        }
    }
}
