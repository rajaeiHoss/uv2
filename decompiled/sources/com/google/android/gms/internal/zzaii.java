package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzaii implements ThreadFactory {
    private final AtomicInteger zzdcf = new AtomicInteger(1);
    private /* synthetic */ String zzdfm;

    zzaii(String str) {
        this.zzdfm = str;
    }

    public final Thread newThread(Runnable runnable) {
        String str = this.zzdfm;
        int andIncrement = this.zzdcf.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23);
        sb.append("AdWorker(");
        sb.append(str);
        sb.append(") #");
        sb.append(andIncrement);
        return new Thread(runnable, sb.toString());
    }
}
