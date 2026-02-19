package com.google.android.gms.gcm;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzb implements ThreadFactory {
    private final AtomicInteger zzijw = new AtomicInteger(1);

    zzb(GcmTaskService gcmTaskService) {
    }

    public final Thread newThread(Runnable runnable) {
        int andIncrement = this.zzijw.getAndIncrement();
        StringBuilder sb = new StringBuilder(20);
        sb.append("gcm-task#");
        sb.append(andIncrement);
        Thread thread = new Thread(runnable, sb.toString());
        thread.setPriority(4);
        return thread;
    }
}
