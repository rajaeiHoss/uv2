package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzagw implements ThreadFactory {
    private final AtomicInteger zzdcf = new AtomicInteger(1);

    zzagw(zzagu zzagu) {
    }

    public final Thread newThread(Runnable runnable) {
        int andIncrement = this.zzdcf.getAndIncrement();
        StringBuilder sb = new StringBuilder(42);
        sb.append("AdWorker(SCION_TASK_EXECUTOR) #");
        sb.append(andIncrement);
        return new Thread(runnable, sb.toString());
    }
}
