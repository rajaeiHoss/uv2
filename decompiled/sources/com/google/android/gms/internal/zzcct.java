package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzcct {
    private Object zzibp = new Object();
    private Handler zzibq;
    private boolean zzibr;
    private HashMap<String, AtomicInteger> zzibs;
    private int zzibt;

    public zzcct(Looper looper, int i) {
        this.zzibq = new Handler(looper);
        this.zzibs = new HashMap<>();
        this.zzibt = 1000;
    }

    /* access modifiers changed from: private */
    public final void zzauz() {
        synchronized (this.zzibp) {
            this.zzibr = false;
            flush();
        }
    }

    public final void flush() {
        synchronized (this.zzibp) {
            for (Map.Entry next : this.zzibs.entrySet()) {
                zzu((String) next.getKey(), ((AtomicInteger) next.getValue()).get());
            }
            this.zzibs.clear();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzu(String str, int i);

    public final void zzv(String str, int i) {
        synchronized (this.zzibp) {
            if (!this.zzibr) {
                this.zzibr = true;
                this.zzibq.postDelayed(new zzccu(this), (long) this.zzibt);
            }
            AtomicInteger atomicInteger = this.zzibs.get(str);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                this.zzibs.put(str, atomicInteger);
            }
            atomicInteger.addAndGet(i);
        }
    }
}
