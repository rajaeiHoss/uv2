package com.google.android.gms.internal;

import java.util.Map;

final class zzeie implements Runnable {
    private /* synthetic */ zzegm zznhg;
    private /* synthetic */ zzeib zznhh;

    zzeie(zzeib zzeib, zzegm zzegm) {
        this.zznhh = zzeib;
        this.zznhg = zzegm;
    }

    public final void run() {
        boolean z;
        synchronized (this.zznhh.zznhf) {
            if (this.zznhh.zznhf.containsKey(this.zznhg)) {
                loop0:
                while (true) {
                    z = true;
                    for (Object value : ((Map) this.zznhh.zznhf.get(this.zznhg)).values()) {
                        zzegx zzegx = (zzegx) value;
                        zzegx.interrupt();
                        if (!z || zzegx.zzbyx()) {
                            z = false;
                        }
                    }
                    break loop0;
                }
                if (z) {
                    this.zznhg.stop();
                }
            }
        }
    }
}
